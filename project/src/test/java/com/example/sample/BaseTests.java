package com.example.sample;

import com.example.sample.domain.dto.request.ReqSetProductDTO;
import com.example.sample.domain.dto.request.ReqSignInDTO;
import com.example.sample.domain.dto.request.ReqSignUpMembeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class BaseTests {
    private static final  String DEFAULT_MEMBER_API_URL = "/api/member";
    private static final  String  DEFAULT_ORDER_API_URL = "/api/order";
    private static final  String  DEFAULT_PRODUCT_API_URL = "/api/product";
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext ctx;

    protected ReqSignUpMembeDTO getSignUpSuccessCase() {
        // Given
        ReqSignUpMembeDTO request = new ReqSignUpMembeDTO();
        request.setAddr("테스트 주소입니다");
        request.setEmail("test@gmail.com");
        request.setId("test-case-01");
        request.setPassword("123qwetr1123");
        request.setName("테스트");
        request.setPhone("01012345678");
        return request;
    }

    protected Map getLoginInfo() throws Exception {

        MvcResult make = mockMvc.perform(post(DEFAULT_MEMBER_API_URL + "/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getSignUpSuccessCase()))
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(result -> {})
                .andReturn();
        Map makeResult = objectMapper.readValue(make.getResponse().getContentAsString(), HashMap.class);
        Map makeContent = (Map) makeResult.get("content");

        ReqSignInDTO request = new ReqSignInDTO();
        request.setId(getSignUpSuccessCase().getId());
        request.setPassword(getSignUpSuccessCase().getPassword());


        MvcResult login = mockMvc.perform(post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(result -> {}).andReturn();
        Map loginResult = objectMapper.readValue(login.getResponse().getContentAsString(), HashMap.class);
        Map content = (Map) loginResult.get("content");
        content.put("userIdx", makeContent.get("userIdx"));
        return content;
    }

    protected Long setSignUp() throws Exception {
        MvcResult member= mockMvc.perform(post(DEFAULT_MEMBER_API_URL+"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getSignUpSuccessCase()))
                )
                .andExpect(status().isOk())
                .andDo(result -> {})
                .andReturn();
        Map data = objectMapper.readValue(member.getResponse().getContentAsString(), HashMap.class);
        Map result = (Map) data.get("content");

        String strUserIdx = String.valueOf(result.get("userIdx"));
        return Long.parseLong(strUserIdx);
    }

    private List<ReqSetProductDTO> getProductList(int count){
        List<ReqSetProductDTO> list = new ArrayList<>();
        String productNamePrefix = "새우깡";
        for ( int i = 0 ; i < count ; i++){
            ReqSetProductDTO obj = new ReqSetProductDTO();
            obj.setProductPrice(100);
            obj.setSalePrice(200);
            obj.setProductPrice(100);
            obj.setProductName(productNamePrefix+i);
            obj.setProductType("NR");
            obj.setCategory(1L);
            obj.setEventNum(1L) ;
            list.add(obj);
        }
        return list;
    }

    protected List<Long> makeProduct(int count) throws Exception{
        List<ReqSetProductDTO> list = getProductList(count);
        List<Long> products = new ArrayList<>();
        for( ReqSetProductDTO temp : list){
            MvcResult product = mockMvc.perform(post(DEFAULT_PRODUCT_API_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(temp)))
                    .andDo(result -> {}).andReturn();
            Map result = objectMapper.readValue(product.getResponse().getContentAsString(), HashMap.class);
            Map content = (Map) result.get("content");
            products.add( Long.parseLong(String.valueOf(content.get("productIdx")) ) );
        }
        return products;
    }
}



