package com.example.sample;

import com.example.sample.domain.dto.request.ReqSetProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class ProductTests extends BaseTests {
    private static final  String DEFAULT_API_URL = "/api/product";

    /**
     * 상품등록_성공_테스트
     * @throws Exception
     */
    @Test
    public void product_suscces() throws Exception {
        //given
        ReqSetProductDTO obj = new ReqSetProductDTO();
        obj.setProductPrice(200);
        obj.setSalePrice(100);
        obj.setProductPrice(100);
        obj.setProductName("감자깡");
        obj.setProductType("NR");
        obj.setCategory(1L);
        obj.setEventNum(1L) ;
        //when, then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(obj)))

                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
    /**
     * 상품등록_실패_테스트_가격값이없을경우
     */
    @Test
    public void product_fail_case01() throws Exception {
        //given
        ReqSetProductDTO obj = new ReqSetProductDTO();
        obj.setProductPrice(200);
        obj.setProductPrice(100);
        obj.setProductName("감자깡");
        obj.setProductType("NR");
        obj.setCategory(1L);
        obj.setEventNum(1L) ;
        //when , then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(obj)))

                .andExpect(status().is4xxClientError())
                .andDo(print());
    }


    /**
     * 상품조회_성공_테스트
     * @throws Exception
     */
    @Test
    public void product_fail_case02() throws Exception {
        List<Long> products = makeProduct(10);

        // When, Then
        mockMvc.perform(get(DEFAULT_API_URL+"?size=3")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    /**
     * 상품조회_성공_테스트_IDX_선택
     * @throws Exception
     */
    @Test
    public void product_fail_case03() throws Exception {

        List<Long> products = makeProduct(10);
        Long lastIdx = products.get(products.size()-1);

        // When, Then
        mockMvc.perform(get(DEFAULT_API_URL+"?size=3&tagetIdx="+lastIdx)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    /**
     * 상품조회_실패_테스트_사이즈누락
     * @throws Exception
     */
    @Test
    public void product_fail_case04() throws Exception {
        List<Long> products = makeProduct(10);

        // When, Then
        mockMvc.perform(get(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }


}
