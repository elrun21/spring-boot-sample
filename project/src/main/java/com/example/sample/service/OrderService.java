package com.example.sample.service;

import com.example.sample.common.enums.ResponseCodeEnum;
import com.example.sample.common.utils.CodeGenerator;
import com.example.sample.common.utils.JwtUtils;
import com.example.sample.common.utils.LogUtils;
import com.example.sample.common.utils.ResponseUtils;
import com.example.sample.domain.dto.request.ReqOrderDTO;
import com.example.sample.domain.dto.request.SaleProductInfo;
import com.example.sample.domain.dto.response.ResOrderDTO;
import com.example.sample.domain.dto.response.ResOrderProductDetail;
import com.example.sample.domain.entity.Member;
import com.example.sample.domain.entity.OrderInfo;
import com.example.sample.domain.entity.OrderProduct;
import com.example.sample.domain.entity.ProductInfo;
import com.example.sample.repository.MemberRepository;
import com.example.sample.repository.OrderProductRepository;
import com.example.sample.repository.OrderRepository;
import com.example.sample.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final ResponseUtils response;
    private final LogUtils logUtils;
    private final CodeGenerator generator;
    private final JwtUtils jwt;

    @Transactional
    public ResponseEntity makeOrder(ReqOrderDTO data) {
        try {

            String orderNumber = generator.makeCode("OR");
            Member member = memberRepository.findByIdx(data.getUserIdx());
            List<SaleProductInfo> list = data.getProductInfo();

            int totalPrice = 0;
            int productStrCnt = list.size() - 1;
            String productName = "";
            OrderInfo order = orderRepository.save(
                    OrderInfo.OrderBuilder.aProduct()
                            .withOrderNo(orderNumber)
                            .withUserId(member.getId())
                            .withProductName(productName)
                            .withAddr(data.getAddress())
                            .withReceiver(data.getReceiver())
                            .withPhone(data.getPhone())
                            .withPaymentPrice(0)
                            .withPaymentType(data.getPaymentType())
                            .withUserIdx(member)
                            .build()
            );

            for (SaleProductInfo info : list) {
                long idx = info.getProductIdx();
                int cnt = info.getProductCount();
                if (idx == 0 || cnt <= 0) {
                    return response.makeOtherResponse(
                            HttpStatus.BAD_REQUEST
                            , ResponseCodeEnum.ORDER_NOT_CREATE_PRODUCT.getDesc()
                            , ResponseCodeEnum.ORDER_NOT_CREATE_PRODUCT.getCode()
                    );
                }
                ProductInfo product = productRepository.findByIdx(idx);
                if (product == null) {
                    return response.makeOtherResponse(
                            HttpStatus.BAD_REQUEST
                            , ResponseCodeEnum.ORDER_NOT_CREATE_PRODUCT_IDX.getDesc()
                            , ResponseCodeEnum.ORDER_NOT_CREATE_PRODUCT_IDX.getCode()
                    );
                }


                if (productName.equals("")) {
                    productName = product.getProductName() + " 외 " + productStrCnt + "건";
                }
                int sumPrice = product.getSalePrice() * cnt;
                totalPrice += sumPrice;
                orderProductRepository.save(OrderProduct.OrderProductBuilder.aOrderProduct()
                        .withProductCount(cnt)
                        .withProductPrice(product.getOriginPrice())
                        .withSalePrice(product.getSalePrice())
                        .withTotalPrice(sumPrice)
                        .withOrderIdx(order)
                        .withProductIdx(product)
                        .build()
                );
            }
            order.updateProductName(productName);
            order.updatePaymentPrice(totalPrice);
            order.updateProductCount(list.size());
            orderRepository.save(order);

            ResOrderDTO result = new ResOrderDTO();
            result.setOrderIdx(order.getIdx());
            result.setOrderNumber(orderNumber);
            result.setProductName(productName);
            result.setTotalPrice(totalPrice);
            result.setTotalCount(productStrCnt + 1);
            result.setPaymentType(order.getPaymentType());
            result.setOrderDate(order.getCreateAt());
            result.setSender(member.getId());
            result.setReceiver(order.getReceiver());
            result.setAddr(order.getAddr());
            return response.makeSuccessResponse(result);

        } catch (Exception e) {
            e.printStackTrace();
            logUtils.loggerAgent(e);
            throw new RuntimeException("OrderMakeException");
        }
    }

    public ResponseEntity findOrder(String token, Long targetIdx, String productName, int limit) {
        Long userIdx = jwt.getUserIdx(token);
        Member member = memberRepository.findByIdx(userIdx);
        if (limit == 0) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        List<ResOrderDTO> result = orderRepository.findOrder(member, targetIdx, productName, limit);
        return response.makeSuccessResponse(result);
    }

    public ResponseEntity findOrderProductDetail(String token, Long orderIdx) {
        try {
            Long userIdx = jwt.getUserIdx(token);
            if (userIdx == 0) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
            Member member = memberRepository.findByIdx(userIdx);
            if (member == null) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
            OrderInfo order = orderRepository.findById(orderIdx).get();
            if (order == null) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
            List<OrderProduct> result = orderProductRepository.findAllByOrderIdx(order);
            List<ResOrderProductDetail> list = new ArrayList<>();
            for (OrderProduct obj : result) {
                list.add(
                        ResOrderProductDetail.builder()
                                .productCount(obj.getProductCount())
                                .productPrice(obj.getProductPrice())
                                .salePrice(obj.getSalePrice())
                                .totalPrice(obj.getTotalPrice())
                                .orderIdx(obj.getOrderIdx().getIdx())
                                .productIdx(obj.getProductIdx().getIdx())
                                .createAt(obj.getCreateAt())
                                .build()
                );
            }

            return response.makeSuccessResponse(list);
        } catch (Exception e) {
            e.printStackTrace();
            logUtils.loggerAgent(e);
            return response.makeOtherResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
