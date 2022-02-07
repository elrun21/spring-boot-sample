package com.example.sample.controller;


import com.example.sample.common.dto.CommonResponseDTO;
import com.example.sample.domain.dto.request.ReqSignUpMembeDTO;
import com.example.sample.service.MemberService;
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
@RequestMapping(value="/api/order")
@Api(tags= {"주문 API"})
public class OrderController {
    private final OrderService service;

    @ApiOperation(value = "상품 주문", response = CommonResponseDTO.class)
    @PostMapping("/order")
    public ResponseEntity getProduct(@Valid @RequestBody ReqOrderDTO data) throws Exception  {
        return service.makeOrder(lastIdx, prevIdx, size);

    }

    @ApiOperation(value = "주문 내역 상세 조회" , response = CommonResponseDTO.class)
    @GetMapping("/order/{idx}")
    public ResponseEntity getOrderDetail(
            @ApiParam(value = "검색할 user idx 값" , required = true)
            @PathVariable Long idx
    ) {
        return  service.findOrderDetail(idx);
    }

    @ApiOperation(value = "주문 내역 조회" , response = CommonResponseDTO.class)
    @GetMapping("/order")
    public ResponseEntity getOrder(
            @ApiParam(value = "검색할 user idx 값" , required = true)
            @PathVariable Long idx
    ) {
        return  service.findOrder(idx);
    }
}
