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
            @ApiParam(value = "검색기준 상품ID 값", required = false)
            @RequestParam(required = false) Long targetIdx,
            @ApiParam(value = "검색기준 상품 명", required = false)
            @RequestParam(required = false) String name,
            @ApiParam(value = "한페이지 사이즈 값 ( 0 은 불가능 ) ", required = true)
            @RequestParam(required = true) int size) {
        return service.findProduct(targetIdx, name ,size);
    }


    @ApiOperation(value = "상품 생성", response = CommonResponseDTO.class)
    @PostMapping
    public ResponseEntity makeProduct( @Valid @RequestBody ReqSetProductDTO data) {
        return service.setProduct(data);
    }
}
