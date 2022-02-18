package com.example.sample.controller;

import com.example.sample.common.dto.CommonResponseDTO;
import com.example.sample.domain.dto.request.ReqSignInDTO;
import com.example.sample.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/api/auth")
@Api(tags= {"인증 API"})
public class AuthController {
    private final AuthService service;

    @ApiOperation(value = "로그인" , response = CommonResponseDTO.class)
    @PostMapping("/sign-in")
    public ResponseEntity signIn(@Valid  @RequestBody ReqSignInDTO data ) throws Exception {
        return  service.signIn(data);
    }

    @ApiOperation(value = "로그아웃" , response = CommonResponseDTO.class)
    @PostMapping("/sign-out" )
    public ResponseEntity signOut(
            @Valid
            @ApiParam(value = "(Header)로그인시 받은 토큰", required = true)
            @RequestHeader(value = "token") String token
    ) {
        return  service.signOut(token );
    }


}
