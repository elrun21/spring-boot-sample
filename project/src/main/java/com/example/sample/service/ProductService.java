package com.example.sample.service;

import com.example.sample.common.utils.LogUtils;
import com.example.sample.common.utils.ResponseUtils;
import com.example.sample.domain.dto.request.ReqSetProductDTO;
import com.example.sample.domain.entity.Product;
import com.example.sample.repository.MemberCustomRepository;
import com.example.sample.repository.MemberInfoCustomRepository;
import com.example.sample.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ResponseUtils response;
    private final LogUtils logUtils;
    private final ProductRepository productCustomRepository;
    private final MemberCustomRepository a;

    public ResponseEntity findProduct(Long lastIdx, Long prevIdx, int limit) {
        if( limit == 0 ) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        if( lastIdx != 0 && prevIdx !=0 ) response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        if ( lastIdx !=0 ){
            return response.makeSuccessResponse( productCustomRepository.findProduct( lastIdx , true , limit ) );
        }else{
            return response.makeSuccessResponse( productCustomRepository.findProduct( prevIdx , false , limit ) );
        }
    }
    @Transactional
    public ResponseEntity setProduct(ReqSetProductDTO data) {
        try {
            Product newProduct = productCustomRepository.save(Product.ProductBuilder.aProduct()
                    .withSalePrice(data.getSalePrice())
                    .withOriginPrice(data.getOriginPrice())
                    .withProductName(data.getProductName())
                    .withProductType(data.getProductType())
                    .withCategory(data.getCategory())
                    .withEventNum(data.getEventNum())
                    .build()
            );
            if (newProduct == null) return response.makeOtherResponse(HttpStatus.BAD_REQUEST, "product info is not create", 202);
            return response.makeSuccessResponse(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            log.error(logUtils.getErrorLog(e));
            throw new RuntimeException("ProductCreateException");
        }
    }
}
