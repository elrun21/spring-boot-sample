package com.example.sample;

import com.example.sample.domain.dto.request.ReqSignInDTO;
import com.example.sample.domain.dto.request.ReqSignInEmailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
public class AuthTests extends BaseTests{
    private final String  DEFAULT_API_URL = "/api/auth";
    @Test
    public  void 회원_로그인_컨트롤러_테스트() throws Exception {
        ReqSignInDTO request = new ReqSignInDTO();
        request.setId("test-02");
        request.setPassword("1234");

        String url = DEFAULT_API_URL+"/sign-in";
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public  void 회원_로그인_컨트롤러_실패_테스트() throws Exception {
        /** 삭제된 계정 접속**/
        //given
        ReqSignInDTO request = new ReqSignInDTO();
        request.setId("test-01");
        request.setPassword("1234");

        String url = DEFAULT_API_URL+"/sign-in";
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
        /** 비밀번호 틀릴경우 **/
        //given
        request.setId("test-04");
        request.setPassword("1234");
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

        /** 없는 계정 접속**/
        //given
        request.setId("xxxxxxxxxxxnull");
        request.setPassword("1234");
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

        /** 비밀번호 틀릴경우 **/
        //given
        request.setId("test-01");
        request.setPassword("12345");
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public  void 회원_이메일_로그인_컨트롤러_테스트() throws Exception {
        ReqSignInEmailDTO request = new ReqSignInEmailDTO();
        request.setEmail("test@gmail.com");
        request.setPassword("1234");

        String url = DEFAULT_API_URL+"/sign-in";
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public  void 회원_이메일_로그인_컨트롤러_실패_테스트() throws Exception {
        /** 삭제된 계정 접속**/
        //given
        ReqSignInDTO request = new ReqSignInDTO();
        request.setId("test-01");
        request.setPassword("1234");

        String url = DEFAULT_API_URL+"/sign-in";
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
        /** 비밀번호 틀릴경우 **/
        //given
        request.setId("test-04");
        request.setPassword("1234");
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

        /** 없는 계정 접속**/
        //given
        request.setId("xxxxxxxxxxxnull");
        request.setPassword("1234");
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

        /** 비밀번호 틀릴경우 **/
        //given
        request.setId("test-01");
        request.setPassword("12345");
        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }


    @Test
    public  void 회원_로그아웃_컨트롤러_성공() throws Exception {
        String url = DEFAULT_API_URL+"/sign-out";
        // When, Then
        mockMvc.perform(post(url)
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mjc5NjUzNDEsImV4cCI6MTAwMTYyNzk2NTM0MCwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjIwMywiaWQiOiJ0ZXN0LTAyIiwic2Vzc2lvbiI6IlNTNjI3OTY1MzQxODYifQ.NyrsRMaCmfzyhvzItVizJIiTCCRLu5EjHF3h2Wu5XEY" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public  void 회원_로그아웃_컨트롤러_실패_테스트() throws Exception {
        String url = DEFAULT_API_URL+"/sign-out";
        /**    jwt 예외케이스 1 , 토큰 데이터 이상 **/
        // When, Then
        mockMvc.perform(post(url)
                .header("token","eyJhbGciOiJIUzIiJ9.eyJpYXQiOjE2Mjc5NjUzNDEsImV4cCI6MTAwMTYyNzk2NTM0MCwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjIwMywiaWQiOiJ0ZXN0LTAyIiwic2Vzc2lvbiI6IlNTNjI3OTY1MzQxODYifQ.NyrsRMaCmfzyhvzItVizJIiTCCRLu5EjHF3h2Wu5XEY" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());


        /** jwt 예외케이스 1 , expire 된 토큰 사용 **/
        // When, Then
        mockMvc.perform(post(url)
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mjc5NTUyNTQsImV4cCI6MTYyNzk1NzA1NCwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjIwMywiaWQiOiJ0ZXN0LTAyIiwic2Vzc2lvbiI6IlNTNjI3OTU1MjU0VFYifQ.QE-ZYsuV2VUgVS_NgacV0jkZC__JN_qXt8IziwwUifI" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

    }


}
