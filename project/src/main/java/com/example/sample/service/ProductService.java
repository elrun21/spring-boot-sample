package com.example.sample.service;

import com.example.sample.common.enums.ResponseCodeEnum;
import com.example.sample.common.utils.LogUtils;
import com.example.sample.common.utils.ResponseUtils;
import com.example.sample.domain.dto.request.ReqSetProductDTO;
import com.example.sample.domain.dto.response.ResProductDTO;
import com.example.sample.domain.dto.response.ResSetProductDTO;
import com.example.sample.domain.entity.ProductInfo;
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
public class ProductService {
    private final ResponseUtils response;
    private final LogUtils logUtils;
    private final ProductRepository productCustomRepository;


    public ResponseEntity findProduct(Long targetIdx, String name, int limit) {
        if( limit == 0 ) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        List<ResProductDTO> result = productCustomRepository.findProduct(targetIdx,name, limit);
        return response.makeSuccessResponse( result );

    }
    @Transactional
    public ResponseEntity setProduct(ReqSetProductDTO data) {
        try {
            ProductInfo newProductInfo = productCustomRepository.save(ProductInfo.ProductBuilder.aProduct()
                    .withSalePrice(data.getSalePrice())
                    .withProductPrice(data.getProductPrice())
                    .withProductName(data.getProductName())
                    .withProductType(data.getProductType())
                    .withCategory(data.getCategory())
                    .withEventNum(data.getEventNum())
                    .build()
            );
            if (newProductInfo == null) return response.makeOtherResponse(HttpStatus.BAD_REQUEST, ResponseCodeEnum.PRODUCT_NOT_CREATE.getDesc(), ResponseCodeEnum.PRODUCT_NOT_CREATE.getCode());
            return response.makeSuccessResponse(
                    ResSetProductDTO.builder()
                            .productIdx(newProductInfo.getIdx())
                            .build()
            );
        }catch(Exception e){
            log.error(logUtils.getErrorLog(e));
            throw new RuntimeException("ProductCreateException");
        }
    }
}
