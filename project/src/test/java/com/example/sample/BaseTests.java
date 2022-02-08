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
    private  final String DEFAULT_MEMBER_API_URL = "/api/member";
    private final String  DEFAULT_ORDER_API_URL = "/api/order";
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext ctx;

    public ReqSignUpMembeDTO getSignUpSuccessCase() {
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

    public Map getLoginInfo() throws Exception {

        MvcResult make = mockMvc.perform(post(DEFAULT_MEMBER_API_URL + "/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getSignUpSuccessCase()))
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
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
                .andDo(print()).andReturn();
        Map loginResult = objectMapper.readValue(login.getResponse().getContentAsString(), HashMap.class);
        Map content = (Map) loginResult.get("content");
        content.put("userIdx", makeContent.get("userIdx"));
        return content;
    }

    public Long setSignUp() throws Exception {
        MvcResult member= mockMvc.perform(post(DEFAULT_MEMBER_API_URL+"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getSignUpSuccessCase()))
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        Map data = objectMapper.readValue(member.getResponse().getContentAsString(), HashMap.class);
        Map result = (Map) data.get("content");

        String strUserIdx = String.valueOf(result.get("userIdx"));
        return Long.parseLong(strUserIdx);
    }

    private List<ReqSetProductDTO> getProductList(int count){
        List<ReqSetProductDTO> list = new ArrayList<>();
        String productNamePrefix = "새우깡";
        for ( int i = 1 ; i < count ; count++){
            list.add(
                    ReqSetProductDTO.builder()
                            .salePrice(200)
                            .productPrice(100)
                            .productName(productNamePrefix+i)
                            .productType("NR")
                            .category(1L)
                            .eventNum(1L)
                            .build()
            );
        }
        return list;
    }

    public List<Long> makeProduct(int count) throws Exception{
        List<ReqSetProductDTO> list = getProductList(count);
        List<Long> products = new ArrayList<>();
        for( ReqSetProductDTO temp : list){
            MvcResult product = mockMvc.perform(post(DEFAULT_ORDER_API_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(temp)))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print()).andReturn();
            Map result = objectMapper.readValue(product.getResponse().getContentAsString(), HashMap.class);
            Map content = (Map) result.get("content");
            products.add( Long.parseLong(String.valueOf(content.get("productIdx")) ) );
        }
        return products;
    }
}



