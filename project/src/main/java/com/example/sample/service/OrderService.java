package com.example.sample.service;

import com.example.sample.common.utils.CodeGenerator;
import com.example.sample.common.utils.JwtUtils;
import com.example.sample.common.utils.LogUtils;
import com.example.sample.common.utils.ResponseUtils;
import com.example.sample.domain.dto.request.ReqOrderDTO;
import com.example.sample.domain.dto.response.ResOrderDTO;
import com.example.sample.domain.dto.response.ResOrderDetailDTO;
import com.example.sample.domain.entity.Member;
import com.example.sample.domain.entity.OrderInfo;
import com.example.sample.domain.entity.ProductInfo;
import com.example.sample.repository.MemberRepository;
import com.example.sample.repository.OrderRepository;
import com.example.sample.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ResponseUtils response;
    private final LogUtils logUtils;
    private final CodeGenerator generator;
    private final JwtUtils jwt ;

    @Transactional
    public ResponseEntity makeOrder(ReqOrderDTO data) {
        try {
            String orderNumber = generator.makeCode("OR");
            Member member = memberRepository.findByIdx(data.getUserIdx());
            ProductInfo product = productRepository.findByIdx(data.getProductIdx());
            OrderInfo order = orderRepository.save(
                    OrderInfo.OrderBuilder.aProduct()
                            .withOrderNo(orderNumber)
                            .withUserId(member.getId())
                            .withProductName(product.getProductName())
                            .withProductCount(data.getProductCount())
                            .withAddr(data.getAddress())
                            .withReceiver(data.getReceiver())
                            .withPhone(data.getPhone())
                            .withPaymentPrice(data.getProductCount() * product.getSalePrice())
                            .withPaymentType(data.getPaymentType())
                            .withUserIdx(member)
                            .withProductIdx(product)
                            .build()
            );
            return response.makeSuccessResponse(
                    ResOrderDTO.builder()
                            .orderNumber(orderNumber)
                            .productName(product.getProductName())
                            .totalPrice(product.getSalePrice() * data.getProductCount())
                            .totalCount(order.getProductCount())
                            .orderDate(order.getCreateAt())
                            .sender(member.getId())
                            .receiver(order.getReceiver())
                            .addr(order.getAddr())
                            .build()
            );
        }catch (Exception e ) {
            logUtils.getErrorLog(e);
            throw new RuntimeException("OrderMakeException");
        }
    }

    public ResponseEntity findOrder(String token , Long targetIdx, String productName,int limit) {
        Long userIdx = jwt.getUserIdx(token);
        Member member = memberRepository.findByIdx(userIdx);
        if( limit == 0 ) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        List<ResOrderDTO> result = orderRepository.findOrder( member , targetIdx , productName, limit ) ;
        return response.makeSuccessResponse( result );
    }

}
