package com.example.sample;

import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

@Transactional
public class OrderTests extends BaseTests{
    private final String  DEFAULT_API_URL = "/api/order";

    @Test
    public  void 주문등록_성공_테스트() throws Exception {


    }

    @Test
    public  void 주문등록_실패_테스트_필수값없을경우() throws Exception {


    }

    @Test
    public  void 주문등록_실패_테스트_존재하지않는상품주문() throws Exception {


    }

    @Test
    public  void 주문등록_실패_테스트_존재하지않는사용자() throws Exception {


    }
}
