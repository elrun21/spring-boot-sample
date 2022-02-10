package com.example.sample;

import com.example.sample.domain.dto.request.ReqOrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class OrderTests extends BaseTests{
    private final String  DEFAULT_API_URL = "/api/order";

    @Test
    public  void 주문등록_성공_테스트() throws Exception {
        // Given
        List<Long> products = makeProduct(1);
        Long userIdx = Long.parseLong( String.valueOf(getLoginInfo().get("userIdx") ));

        ReqOrderDTO request = new ReqOrderDTO();
        request.setUserIdx(userIdx);
        request.setProductIdx( products.get(0));
        request.setProductCount(3);
        request.setAddress("샘플주소지");
        request.setReceiver("홍길동");
        request.setPhone("01012340987");
        request.setPaymentType("CARD");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is2xxSuccessful() )
                .andDo(print());

    }

    @Test
    public  void 주문등록_실패_테스트_주소없을경우() throws Exception {
        // Given
        List<Long> products = makeProduct(1);
        Long userIdx = Long.parseLong( String.valueOf(getLoginInfo().get("userIdx") ));

        ReqOrderDTO request = new ReqOrderDTO();
        request.setUserIdx(userIdx);
        request.setProductIdx( products.get(0));
        request.setProductCount(3);
        request.setReceiver("홍길동");
        request.setPhone("01012340987");
        request.setPaymentType("CARD");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError() )
                .andDo(print());

    }

    @Test
    public  void 주문등록_실패_테스트_전화번호없을경우() throws Exception {
        // Given
        List<Long> products = makeProduct(1);
        Long userIdx = Long.parseLong( String.valueOf(getLoginInfo().get("userIdx") ));

        ReqOrderDTO request = new ReqOrderDTO();
        request.setUserIdx(userIdx);
        request.setProductIdx( products.get(0));
        request.setProductCount(3);
        request.setAddress("샘플주소지");
        request.setReceiver("홍길동");
        request.setPaymentType("CARD");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError() )
                .andDo(print());

    }

    @Test
    public  void 주문등록_실패_테스트_결제수단없을경우() throws Exception {
        // Given
        List<Long> products = makeProduct(1);
        Long userIdx = Long.parseLong( String.valueOf(getLoginInfo().get("userIdx") ));

        ReqOrderDTO request = new ReqOrderDTO();
        request.setUserIdx(userIdx);
        request.setProductIdx( products.get(0));
        request.setProductCount(3);
        request.setAddress("샘플주소지");
        request.setReceiver("홍길동");
        request.setPhone("01012340987");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError() )
                .andDo(print());

    }

    @Test
    public  void 주문등록_실패_테스트_구매수량없을경우() throws Exception {
        // Given
        List<Long> products = makeProduct(1);
        Long userIdx = Long.parseLong( String.valueOf(getLoginInfo().get("userIdx") ));

        ReqOrderDTO request = new ReqOrderDTO();
        request.setUserIdx(userIdx);
        request.setProductIdx( products.get(0));
        request.setAddress("샘플주소지");
        request.setReceiver("홍길동");
        request.setPhone("01012340987");
        request.setPaymentType("CARD");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError() )
                .andDo(print());

    }

    @Test
    public  void 주문등록_실패_테스트_존재하지않는상품주문() throws Exception {

        // Given
        List<Long> products = makeProduct(10);
        Long userIdx = Long.parseLong( String.valueOf(getLoginInfo().get("userIdx") ));

        ReqOrderDTO request = new ReqOrderDTO();
        request.setProductIdx((long) 0 );
        request.setProductCount(3);
        request.setAddress("샘플주소지");
        request.setReceiver("홍길동");
        request.setPhone("01012340987");
        request.setPaymentType("CARD");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError() )
                .andDo(print());
    }

    @Test
    public  void 주문등록_실패_테스트_존재하지않는사용자() throws Exception {

        // Given
        List<Long> products = makeProduct(1);

        ReqOrderDTO request = new ReqOrderDTO();
        request.setUserIdx(0L);
        request.setProductIdx( products.get(0));
        request.setProductCount(3);
        request.setAddress("샘플주소지");
        request.setReceiver("홍길동");
        request.setPhone("01012340987");
        request.setPaymentType("CARD");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError() )
                .andDo(print());
    }


    @Test
    public  void 주문내역조회_성공_테스트_최초조회() throws Exception {

        // Given
        List<Long> products = makeProduct(1);
        Map login = getLoginInfo();
        Long userIdx = Long.parseLong( String.valueOf(login.get("userIdx") ));
        String token = String.valueOf(login.get("token"));

        ReqOrderDTO request = new ReqOrderDTO();
        request.setUserIdx(userIdx);
        request.setProductIdx( products.get(0));
        request.setProductCount(3);
        request.setAddress("샘플주소지");
        request.setReceiver("홍길동");
        request.setPhone("01012340987");
        request.setPaymentType("CARD");

        for ( int i = 0 ; i < 5 ; i++) {
            mockMvc.perform(post(DEFAULT_API_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )
                    .andDo(print()).andReturn();
        }

        // When, Then
        mockMvc.perform(get(DEFAULT_API_URL+"?size=3")
                        .header("token",token )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful() )
                .andDo(print());

    }

    @Test
    public  void 주문내역조회_성공_테스트_IDX_선택() throws Exception {

        // Given
        List<Long> products = makeProduct(1);
        Map login = getLoginInfo();
        Long userIdx = Long.parseLong( String.valueOf(login.get("userIdx") ));
        String token = String.valueOf(login.get("token"));

        ReqOrderDTO request = new ReqOrderDTO();
        request.setUserIdx(userIdx);
        request.setProductIdx( products.get(0));
        request.setProductCount(3);
        request.setAddress("샘플주소지");
        request.setReceiver("홍길동");
        request.setPhone("01012340987");
        request.setPaymentType("CARD");

        List<Long> orders = new ArrayList<>();
        for ( int i = 0 ; i < 5 ; i++) {
            MvcResult order = mockMvc.perform(post(DEFAULT_API_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))

                    )
                    .andDo(print()).andReturn();
            Map result = objectMapper.readValue(order.getResponse().getContentAsString(), HashMap.class);
            Map content = (Map) result.get("content");
            orders.add( Long.parseLong(String.valueOf(content.get("orderIdx")) ) );

        }

        // When, Then
        Long idx = orders.get(orders.size()-1);
        mockMvc.perform(get(DEFAULT_API_URL+"?size=3&&targetIdx="+idx)
                        .header("token",token )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful() )
                .andDo(print());

    }


    @Test
    public  void 주문내역조회_실패_테스트_토큰이없을때() throws Exception {

        // Given
        List<Long> products = makeProduct(1);
        Map login = getLoginInfo();
        Long userIdx = Long.parseLong( String.valueOf(login.get("userIdx") ));


        ReqOrderDTO request = new ReqOrderDTO();
        request.setUserIdx(userIdx);
        request.setProductIdx( products.get(0));
        request.setProductCount(3);
        request.setAddress("샘플주소지");
        request.setReceiver("홍길동");
        request.setPhone("01012340987");
        request.setPaymentType("CARD");

        List<Long> orders = new ArrayList<>();
        for ( int i = 0 ; i < 5 ; i++) {
            MvcResult order = mockMvc.perform(post(DEFAULT_API_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))

                    )
                    .andDo(result -> {}).andReturn();
            Map result = objectMapper.readValue(order.getResponse().getContentAsString(), HashMap.class);
            Map content = (Map) result.get("content");
            orders.add( Long.parseLong(String.valueOf(content.get("orderIdx")) ) );

        }

        // When, Then
        Long idx = orders.get(orders.size()-1);
        mockMvc.perform(get(DEFAULT_API_URL+"?size=3&&targetIdx="+idx)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError() )
                .andDo(print());

    }




}
