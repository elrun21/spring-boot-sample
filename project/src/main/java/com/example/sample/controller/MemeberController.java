package com.example.sample.controller;

import com.example.sample.common.dto.CommonResponseDTO;
import com.example.sample.domain.dto.request.ReqMemberModifyDTO;
import com.example.sample.domain.dto.request.ReqSignUpMembeDTO;
import com.example.sample.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/member/")
@Api(tags= {"회원 API"})
public class MemeberController {
    private final MemberService service;

    @ApiOperation(value = "회원 가입" , response = CommonResponseDTO.class)
    @PostMapping("sign-up")
    public ResponseEntity signUp(  @Valid @RequestBody ReqSignUpMembeDTO data ) throws Exception {
        return  service.signUp(data);
    }

    @ApiOperation(value = "회원 탈퇴" , response = CommonResponseDTO.class)
    @DeleteMapping("{idx}")
    public ResponseEntity deleteUser(
            @ApiParam(value = "(Header)로그인시 받은 토큰", required = true)
            @RequestHeader(value = "token") String token,
            @PathVariable String idx
    ) {
         return  service.deleteUser(token , idx );
    }

    @ApiOperation(value = "회원 정보 조회" , response = CommonResponseDTO.class)
    @GetMapping("{idx}")
    public ResponseEntity getUser(
        @ApiParam(value = "검색할 user idx 값" , required = true)
        @PathVariable("idx") String idx
    ) {

        return  service.findUserOne(idx);
    }


    @ApiOperation(value = "회원 정보 수정" , response = CommonResponseDTO.class)
    @PatchMapping("{idx}")
    public ResponseEntity updateMember(
            @ApiParam(value = "(Header)로그인시 받은 토큰", required = true)
            @RequestHeader(value = "token") String token,
            @ApiParam(value = "마지막 user Idx 값 , 이전 페이지 경우 마지막 idx + size*2 값으로 호출" , required = true)
            @Valid
            @RequestBody ReqMemberModifyDTO data ) {
        return  service.updateMemberInfo( data , token );
    }


}
