package com.example.sample.service;

import com.example.sample.common.utils.LogUtils;
import com.example.sample.common.utils.ResponseUtils;
import com.example.sample.repository.ProductCustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    private final ResponseUtils response;
    private final LogUtils logUtils;
    private final ProductCustomRepository productCustomRepository;


    public ResponseEntity findProduct(Long lastIdx, Long prevIdx, int limit) {
        if( limit == 0 ) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        if( lastIdx != 0 && prevIdx !=0 ) response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        if ( lastIdx !=0 ){
            return response.makeSuccessResponse( productCustomRepository.findProduct( lastIdx , true , limit ) );
        }else{
            return response.makeSuccessResponse( productCustomRepository.findProduct( prevIdx , false , limit ) );
        }
    }
}
