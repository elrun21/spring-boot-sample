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
import javax.websocket.server.PathParam;

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
            @ApiParam(value = "(Header)로그인시 받은 토큰", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(value = "검색기준 상품ID 값", required = false)
            @RequestParam(required = false) Long targetIdx,
            @ApiParam(value = "검색기준 상품 명", required = false)
            @RequestParam(required = false) String name,
            @ApiParam(value = "한페이지 사이즈 값 ( 0 은 불가능 ) ", required = true)
            @RequestParam(required = true) int size) {
        return service.findOrder( token,targetIdx, name , size);
    }

    @ApiOperation(value = "주문 상품 내역 조회" , response = CommonResponseDTO.class)
    @GetMapping("/{orderIdx}")
    public ResponseEntity getOrder(
            @ApiParam(value = "(Header)로그인시 받은 토큰", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(value = "", required = true)
            @PathVariable("orderIdx") Long orderIdx
            ) {
        return service.findOrderProductDetail( token, orderIdx);
    }

}
