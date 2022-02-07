package com.example.sample.controller;


import com.example.sample.common.dto.CommonResponseDTO;
import com.example.sample.domain.dto.request.ReqOrderDTO;
import com.example.sample.domain.dto.request.ReqSignUpMembeDTO;
import com.example.sample.service.MemberService;
import com.example.sample.service.OrderService;
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
    @PostMapping
    public ResponseEntity makeOrder(@Valid @RequestBody ReqOrderDTO data) throws Exception  {
        return service.makeOrder(data);

    }


    @ApiOperation(value = "주문 내역 조회" , response = CommonResponseDTO.class)
    @GetMapping
    public ResponseEntity getOrder(
            @ApiParam(value = "[next 일 경우] 리스트 마지막 주문 IDX 값", required = true)
            @RequestParam("lastIdx") Long lastIdx,
            @ApiParam(value = "[prev 일 경우] 리스트 처음 주문 IDX 값", required = true)
            @RequestParam("prevIdx") Long prevIdx,
            @ApiParam(value = "한페이지 사이즈 값 ( 0 은 불가능 ) ", required = true)
            @RequestParam("size") int size) {
        return service.findOrder(lastIdx, prevIdx, size);
    }
}
