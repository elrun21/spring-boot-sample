package com.example.sample;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ProductTests extends BaseTests{
    private final String  DEFAULT_API_URL = "/api/product";


    @Test
    public  void 상품등록_성공_테스트() throws Exception {


    }

    @Test
    public  void 상품등록_실패_테스트_값이몇개없을경우() throws Exception {


    }

    @Test
    public  void 상품조회_성공_테스트() throws Exception {


    }

    @Test
    public  void 상품조회_실패_테스트() throws Exception {


    }

    @Test
    public  void 상품조회_실패_테스트_파라미터누락() throws Exception {


    }

    @Test
    public  void 상품조회_실패_테스트_사이즈정보_zero() throws Exception {


    }
}
