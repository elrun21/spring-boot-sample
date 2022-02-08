package com.example.sample;

import com.example.sample.domain.dto.request.ReqMemberModifyDTO;
import com.example.sample.domain.dto.request.ReqSignInDTO;
import com.example.sample.domain.dto.request.ReqSignUpMembeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class MemberTests extends BaseTests{
    private final String  DEFAULT_API_URL = "/api/member";

    @Test
    public  void 회원_가입_성공_테스트() throws Exception {

        MvcResult member= mockMvc.perform(post(DEFAULT_API_URL+"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getSignUpSuccessCase()))
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public  void 회원_가입_실패_긴아이디() throws Exception {
        // Given
        ReqSignUpMembeDTO request = new ReqSignUpMembeDTO();
        request.setAddr("테스트 주소입니다");
        request.setEmail("test@gmail.com");
        request.setId("test-case-01aaaaaaaskdlksadjalkfjlkajdlkajsdlkajsdlkajflkjaflkjaldkasjlaskdjlkfjlafj");
        request.setPassword("123qwetr1123");
        request.setName("테스트");
        request.setPhone("01012345678");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL + "/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    public  void 회원_가입_실패_아이디가없는경우() throws Exception {
        // Given
        /** 아이디 정보가 없을 경우 **/
        // Given
        ReqSignUpMembeDTO request = new ReqSignUpMembeDTO();
        request.setAddr("테스트 주소입니다");
        request.setEmail("test@gmail.com");
        request.setPassword("123qwetr1123");
        request.setName("테스트");
        request.setPhone("01012345678");
        request.setId(null);
        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL + "/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    public  void 회원_가입_실패_비밀번호가없음() throws Exception {
        // Given
        /** 패스워드 정보가 없을 경우 **/
        ReqSignUpMembeDTO request = new ReqSignUpMembeDTO();
        request.setAddr("테스트 주소입니다");
        request.setEmail("test@gmail.com");
        request.setId("test-case-01");
        request.setPassword(null);
        request.setName("테스트");
        request.setPhone("01012345678");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL+"/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    public void 회원_탈퇴_성공() throws Exception{
        String token = (String) getLoginInfo().get("token");
        // When, Then
        mockMvc.perform(delete(DEFAULT_API_URL+"/"+getSignUpSuccessCase().getId())
                .header("token",token )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }



    @Test
    public void 회원_탈퇴_실패() throws Exception{
        Map loginInfo = getLoginInfo();
        String token = (String) loginInfo.get("token");
        String userIdx = String.valueOf(loginInfo.get("userIdx"));

        /** 토큰이 없을경우  **/
        // When, Then
        mockMvc.perform(delete(DEFAULT_API_URL+"/"+userIdx)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());


        /** jwt 예외케이스 1 , 토큰 데이터 이상 **/
        // When, Then
        mockMvc.perform(delete(DEFAULT_API_URL+"/"+userIdx)
                .header("token","yJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjgxNTIzOTYsImV4cCI6MTcyODE1MjM5NSwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjE1NzU1LCJpZCI6InRlc3QtYS0xIiwic2Vzc2lvbiI6IlNTNjI4MTUyMzk2OWYifQ.qJpX7XSjb-W-XkH-1tQWiPbfTYW-PTQfKzrhWwfqyH8" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

        /** jwt 예외케이스 1 , expire 된 토큰 사용 **/
        // When, Then
        mockMvc.perform(delete(DEFAULT_API_URL+"/"+userIdx)
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mjc5NTUyNTQsImV4cCI6MTYyNzk1NzA1NCwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjIwMywiaWQiOiJ0ZXN0LTAyIiwic2Vzc2lvbiI6IlNTNjI3OTU1MjU0VFYifQ.QE-ZYsuV2VUgVS_NgacV0jkZC__JN_qXt8IziwwUifI" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void 회원_정보조회_성공() throws Exception{
        Map loginInfo = getLoginInfo();
        String token = (String) loginInfo.get("token");
        String userIdx = String.valueOf(loginInfo.get("userIdx"));

        String url = DEFAULT_API_URL+"/"+userIdx;
        mockMvc.perform(get(url)
                        .header("token",token )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
    @Test
    public void 회원_정보조회_실패_잘못된유저정보() throws Exception{

        /** 잘못된 파라미터 입력 **/
        String erroUrl01 = DEFAULT_API_URL+"/user/asdf";
        mockMvc.perform(get(erroUrl01) )
                .andExpect(status().is4xxClientError())
                .andDo(print());

    }



    @Test
    public void 회원_정보수정_성공() throws Exception{
        Map loginInfo = getLoginInfo();
        String token = (String) loginInfo.get("token");
        String userIdx = String.valueOf(loginInfo.get("userIdx"));

        ReqMemberModifyDTO request = new ReqMemberModifyDTO();
        request.setAddr("테스트 케이스 실행-주소");
        request.setId(getSignUpSuccessCase().getId());
        request.setPhone("01099999999");
        // When, Then
        mockMvc.perform(patch(DEFAULT_API_URL+"/"+userIdx)
                .header("token",token )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }


    @Test
    public void 회원_정보수정_실패_모든값이없을경우() throws Exception {
        Map loginInfo = getLoginInfo();
        String token = (String) loginInfo.get("token");
        String userIdx = String.valueOf(loginInfo.get("userIdx"));
        ReqMemberModifyDTO request = new ReqMemberModifyDTO();

        /** 모든값을 null **/
        request.setAddr(null);
        request.setId(null);
        request.setPhone(null);
        mockMvc.perform(patch(DEFAULT_API_URL+"/"+userIdx)
                        .header("token", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void 회원_정보수정_실패_주소_핸드폰_정보없음() throws Exception {
        //given
        Map loginInfo = getLoginInfo();
        String token = (String) loginInfo.get("token");
        String userIdx = String.valueOf(loginInfo.get("userIdx"));

        /** 주소,핸드폰데이터 null **/
        ReqMemberModifyDTO request = new ReqMemberModifyDTO();
        request.setAddr(null);
        request.setId("test-a-1");
        request.setPhone(null);
        mockMvc.perform(patch(DEFAULT_API_URL+"/"+userIdx)
                        .header("token", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
    @Test
    public void 회원_정보수정_실패_핸드폰정보가_길때() throws Exception {
        //given
        Map loginInfo = getLoginInfo();
        String token = (String) loginInfo.get("token");
        String userIdx = String.valueOf(loginInfo.get("userIdx"));
        /** 핸드폰 데이터 길이 **/
        ReqMemberModifyDTO request = new ReqMemberModifyDTO();
        request.setAddr("변경테스트");
        request.setId("test-a-1");
        request.setPhone("11111111111111111");
        mockMvc.perform(patch(DEFAULT_API_URL+"/"+userIdx)
                .header("token",token )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void 회원_정보수정_실패_토큰이없을때() throws Exception {
        //given
        Map loginInfo = getLoginInfo();
        String userIdx = String.valueOf(loginInfo.get("userIdx"));
        /** 핸드폰 데이터 길이 **/
        ReqMemberModifyDTO request = new ReqMemberModifyDTO();
        request.setId("test-a-1");
        mockMvc.perform(patch(DEFAULT_API_URL+"/"+userIdx)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}
