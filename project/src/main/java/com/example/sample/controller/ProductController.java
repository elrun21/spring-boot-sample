package com.example.sample.controller;


import com.example.sample.common.dto.CommonResponseDTO;
import com.example.sample.domain.dto.request.ReqSetProductDTO;
import com.example.sample.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/product")
@Api(tags= {"상품 API"})
public class ProductController {
    private final ProductService service;

    @ApiOperation(value = "상품 조회", response = CommonResponseDTO.class)
    @GetMapping
    public ResponseEntity getProduct(
            @ApiParam(value = "[next 일 경우] 마지막 상품ID 값", required = true)
            @RequestParam("lastIdx") Long lastIdx,
            @ApiParam(value = "[prev 일 경우] 처음 상품ID 값", required = true)
            @RequestParam("prevIdx") Long prevIdx,
            @ApiParam(value = "한페이지 사이즈 값 ( 0 은 불가능 ) ", required = true)
            @RequestParam("size") int size) {
        return service.findProduct(lastIdx, prevIdx, size);
    }


    @ApiOperation(value = "상품 생성", response = CommonResponseDTO.class)
    @PostMapping
    public ResponseEntity setProduct( @Valid @RequestBody ReqSetProductDTO data) {
        return service.setProduct(data);
    }
}
