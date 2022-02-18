package com.example.sample;

import com.example.sample.domain.dto.request.ReqSignInDTO;
import com.example.sample.domain.dto.request.ReqSignUpMembeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
public class AuthTests extends BaseTests{

    private static final  String url =  "/api/auth/sign-in";
    private static final String url_sign_out =  "/api/auth/sign-out";
    private static final String member_url =  "/api/member/sign-up";

    /**
     * 회원_로그인_컨트롤러_성공_테스트
     * @throws Exception
     */
    @Test
    public  void memberLoginSucess() throws Exception {

        ReqSignUpMembeDTO requestMake = new ReqSignUpMembeDTO();
        requestMake.setId("test-02");
        requestMake.setPassword("1234");
        requestMake.setAddr("testaddr");
        requestMake.setEmail("test02@gmail.com");
        requestMake.setName("test02");
        requestMake.setPhone("01012340002");

        // When, Then
        mockMvc.perform(post(member_url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( objectMapper.writeValueAsString(requestMake) )        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        ReqSignInDTO request = new ReqSignInDTO();
        request.setId("test-02");
        request.setPassword("1234");

        // When, Then
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(request) )        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    /**
     * 회원_로그인_컨트롤러_실패_삭제된계정접속
     * @throws Exception
     */
    @Test
    public  void memberLoginFailCase01() throws Exception {
        /** 삭제된 계정 접속**/
        //given
        ReqSignInDTO request = new ReqSignInDTO();
        request.setId("test-01");
        request.setPassword("1234");


        // When, Then
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    /**
     * 회원_로그인_컨트롤러_실패_비밀번호틀린경우
     * @throws Exception
     */
    @Test
    public  void memberLoginFailCase02() throws Exception {
        ReqSignUpMembeDTO requestMake = new ReqSignUpMembeDTO();
        requestMake.setId("test-02");
        requestMake.setPassword("1234");
        requestMake.setAddr("testaddr");
        requestMake.setEmail("test02@gmail.com");
        requestMake.setName("test02");
        requestMake.setPhone("01012340002");

        // When, Then
        mockMvc.perform(post(member_url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( objectMapper.writeValueAsString(requestMake) )        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
        /** 비밀번호 틀릴경우 **/
        //given
        ReqSignInDTO request = new ReqSignInDTO();
        request.setId("test-02");
        request.setPassword("1df234");
        // When, Then
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    /**
     * 회원_로그인_컨트롤러_실패_아이디가없음
     * @throws Exception
     */
    @Test
    public  void memberLoginFailCase03() throws Exception {
        /** 없는 계정 접속**/
        //given
        ReqSignInDTO request = new ReqSignInDTO();
        request.setId("xxxxxxxxxxxnull");
        request.setPassword("1234");
        // When, Then
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }


    /**
     * 회원_로그아웃_컨트롤러_성공
     * @throws Exception
     */
    @Test
    public  void memberLoginFailCase04() throws Exception {
        ReqSignUpMembeDTO requestMake = new ReqSignUpMembeDTO();
        requestMake.setId("test-02");
        requestMake.setPassword("1234");
        requestMake.setAddr("testaddr");
        requestMake.setEmail("test02@gmail.com");
        requestMake.setName("test02");
        requestMake.setPhone("01012340002");

        // When, Then
        mockMvc.perform(post(member_url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestMake)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

        ReqSignInDTO request = new ReqSignInDTO();
        request.setId("test-02");
        request.setPassword("1234");

        // When, Then
        MvcResult login = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();
        Map loginResult = objectMapper.readValue(login.getResponse().getContentAsString(), HashMap.class);

        Map content = (Map) loginResult.get("content");
        String token = (String) content.get("token");


        String url = url_sign_out;
        // When, Then
        mockMvc.perform(post(url)
                .header("token",token )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    /**
     * 회원_로그아웃_컨트롤러_실패_테스트
     * @throws Exception
     */
    @Test
    public  void memberLoginFailCase05() throws Exception {


        String url = url_sign_out;
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
