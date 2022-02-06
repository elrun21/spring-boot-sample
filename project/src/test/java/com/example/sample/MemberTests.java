package com.example.sample;

import com.example.sample.domain.dto.request.ReqMemberModifyDTO;
import com.example.sample.domain.dto.request.ReqSignUpMembeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class MemberTests extends BaseTests{
    private final String  DEFAULT_API_URL = "/api/member";

    @Test
    public  void 회원_가입_테스트() throws Exception {
        // Given
        ReqSignUpMembeDTO request = new ReqSignUpMembeDTO();
        request.setAddr("테스트 주소입니다");
        request.setEmail("test@gmail.com");
        request.setId("test-case-01");
        request.setPassword("123qwetr1123");
        request.setName("테스트");
        request.setPhone("01012345678");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL+"/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public  void 회원_가입_실패_테스트() throws Exception {
        // Given
        ReqSignUpMembeDTO request = new ReqSignUpMembeDTO();
        request.setAddr("테스트 주소입니다");
        request.setEmail("test@gmail.com");
        request.setId("test-case-01aaaaaaaskdlksadjalkfjlkajdlkajsdlkajsdlkajflkjaflkjaldkasjlaskdjlkfjlafj");
        request.setPassword("123qwetr1123");
        request.setName("테스트");
        request.setPhone("01012345678");

        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL+"/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

        // Given
        /** 아이디 정보가 없을 경우 **/
        request.setId(null);
        // When, Then
        mockMvc.perform(post(DEFAULT_API_URL+"/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

        // Given
        /** 패스워드 정보가 없을 경우 **/
        request.setId("test-case-01");
        request.setPassword(null);
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
        // When, Then
        mockMvc.perform(delete(DEFAULT_API_URL+"/test-a-1")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjgxNTIzOTYsImV4cCI6MTcyODE1MjM5NSwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjE1NzU1LCJpZCI6InRlc3QtYS0xIiwic2Vzc2lvbiI6IlNTNjI4MTUyMzk2OWYifQ.qJpX7XSjb-W-XkH-1tQWiPbfTYW-PTQfKzrhWwfqyH8" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
    @Test
    public void 회원_탈퇴_실패() throws Exception{

        /** 토큰이 없을경우  **/
        // When, Then
        mockMvc.perform(delete(DEFAULT_API_URL+"/test-a-1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());


        /** jwt 예외케이스 1 , 토큰 데이터 이상 **/
        // When, Then
        mockMvc.perform(delete(DEFAULT_API_URL+"/test-a-1")
                .header("token","yJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjgxNTIzOTYsImV4cCI6MTcyODE1MjM5NSwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjE1NzU1LCJpZCI6InRlc3QtYS0xIiwic2Vzc2lvbiI6IlNTNjI4MTUyMzk2OWYifQ.qJpX7XSjb-W-XkH-1tQWiPbfTYW-PTQfKzrhWwfqyH8" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

        /** jwt 예외케이스 1 , expire 된 토큰 사용 **/
        // When, Then
        mockMvc.perform(delete(DEFAULT_API_URL+"/test-a-1")
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mjc5NTUyNTQsImV4cCI6MTYyNzk1NzA1NCwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjIwMywiaWQiOiJ0ZXN0LTAyIiwic2Vzc2lvbiI6IlNTNjI3OTU1MjU0VFYifQ.QE-ZYsuV2VUgVS_NgacV0jkZC__JN_qXt8IziwwUifI" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void 회원_단일_정보조회_성공() throws Exception{
        String url = DEFAULT_API_URL+"/user/203";
        mockMvc.perform(get(url) )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
    @Test
    public void 회원_단일_정보조회_실패() throws Exception{
        String url = DEFAULT_API_URL+"/user/203";

        /** 잘못된 파라미터 입력 **/
        String erroUrl01 = DEFAULT_API_URL+"/user/asdf";
        mockMvc.perform(get(erroUrl01) )
                .andExpect(status().is4xxClientError())
                .andDo(print());

    }
    @Test
    public void 회원_정보리스트조회_성공() throws Exception{
        String url = DEFAULT_API_URL+"/user?";
        mockMvc.perform(get(url+"size=3&lastIdx=0") )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();

        mockMvc.perform(get(url+"size=3&lastIdx=245") )
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();

    }
    @Test
    public void 회원_정보리스트조회_실패() throws Exception{
        String url = DEFAULT_API_URL+"/user?";
        mockMvc.perform(get(url+"size=0&lastIdx=0") )
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();

        mockMvc.perform(get(url+"lastIdx=245") )
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void 회원_정보수정_성공() throws Exception{
        ReqMemberModifyDTO request = new ReqMemberModifyDTO();
        request.setAddr("테스트 케이스 실행-주소");
        request.setId("test-a-1");
        request.setPhone("01099999999");
        // When, Then
        mockMvc.perform(patch(DEFAULT_API_URL)
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjgxNTIzOTYsImV4cCI6MTcyODE1MjM5NSwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjE1NzU1LCJpZCI6InRlc3QtYS0xIiwic2Vzc2lvbiI6IlNTNjI4MTUyMzk2OWYifQ.qJpX7XSjb-W-XkH-1tQWiPbfTYW-PTQfKzrhWwfqyH8" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void 회원_정보수정_실패() throws Exception{

        ReqMemberModifyDTO request = new ReqMemberModifyDTO();

        /** 모든값을 null **/
        request.setAddr(null);
        request.setId(null);
        request.setPhone(null);
        mockMvc.perform(patch(DEFAULT_API_URL)
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjgxNTIzOTYsImV4cCI6MTcyODE1MjM5NSwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjE1NzU1LCJpZCI6InRlc3QtYS0xIiwic2Vzc2lvbiI6IlNTNjI4MTUyMzk2OWYifQ.qJpX7XSjb-W-XkH-1tQWiPbfTYW-PTQfKzrhWwfqyH8" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
        /** 주소,핸드폰데이터 null **/
        request.setAddr(null);
        request.setId("test-a-1");
        request.setPhone(null);
        mockMvc.perform(patch(DEFAULT_API_URL)
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjgxNTIzOTYsImV4cCI6MTcyODE1MjM5NSwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjE1NzU1LCJpZCI6InRlc3QtYS0xIiwic2Vzc2lvbiI6IlNTNjI4MTUyMzk2OWYifQ.qJpX7XSjb-W-XkH-1tQWiPbfTYW-PTQfKzrhWwfqyH8" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());

        /** 핸드폰 데이터 길이 **/
        request.setAddr("변경테스트");
        request.setId("test-a-1");
        request.setPhone("11111111111111111");
        mockMvc.perform(patch(DEFAULT_API_URL)
                .header("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjgxNTIzOTYsImV4cCI6MTcyODE1MjM5NSwiYXV0aCI6InRlc3QtZGVtb24tYXV0aC1rZXkiLCJpZHgiOjE1NzU1LCJpZCI6InRlc3QtYS0xIiwic2Vzc2lvbiI6IlNTNjI4MTUyMzk2OWYifQ.qJpX7XSjb-W-XkH-1tQWiPbfTYW-PTQfKzrhWwfqyH8" )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}
