package com.example.sample.service;

import com.example.sample.common.config.SecurityProperties;
import com.example.sample.common.dto.TokenDTO;
import com.example.sample.common.enums.MemberEnum;
import com.example.sample.common.utils.CodeGenerator;
import com.example.sample.common.utils.JwtUtils;
import com.example.sample.common.utils.ResponseUtils;
import com.example.sample.common.utils.SecurityUtils;
import com.example.sample.domain.dto.request.ReqSignInEmailDTO;
import com.example.sample.domain.dto.response.ResTokenDTO;
import com.example.sample.domain.dto.request.ReqSignInDTO;
import com.example.sample.domain.entity.Member;
import com.example.sample.domain.entity.MemberAccess;
import com.example.sample.repository.AuthRepository;
import com.example.sample.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    private final AuthRepository repositoryAuth;
    private final MemberRepository repositoryMember;
    private final ResponseUtils response;
    private final JwtUtils jwt ;
    private final CodeGenerator generator;
    private final SecurityProperties securityProperties;
    private final SecurityUtils security;
    /**
     * 로그인
     * @param data
     * @return
     */
    @Transactional
    public ResponseEntity signIn(ReqSignInDTO data) {
        if( data.getId() == null || data.getPassword() ==null ) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        // id, pw  확인
        try {
            Member user = repositoryMember.findByIdAndPassword(data.getId(), security.getAESEncrypt(data.getPassword()));
            return commonSignIn(user);
        }catch (Exception e) {
            return response.makeOtherResponse(HttpStatus.FORBIDDEN);
        }
    }


    /**
     * 로그인 로직 ( 공용 )
     * @param user
     * @return
     */
    public ResponseEntity commonSignIn( Member user ) {
        if( user == null )   return response.makeOtherResponse(HttpStatus.UNAUTHORIZED);
        // 로직 시작 전에 블락/탈퇴 계정은 아닌지 확인
        if( !user.getLiveStatus().equals(MemberEnum.USER_STATE_LIVE.getCode())) return response.makeOtherResponse(HttpStatus.FORBIDDEN);
        // 세션 코드 생성
        String sessionCode = generator.makeCode("SS");
        log.debug("session-id : "+sessionCode);
        // 세션코드 생성 시간 기준은 현재 시간
        LocalDateTime now = LocalDateTime.now();
        // 설정값 기준으로 만료 시간 설정
        LocalDateTime expire = now.plusSeconds(securityProperties.getExpireTime());
        // jwt 토큰 생성
        String token = null;

        token = jwt.createToken(user, sessionCode, now, expire);

        // 생성된 토큰을 포함한 응답 객체 빌드
        MemberAccess memberAccess = MemberAccess.MemberAccessBuilder.aMemberAccess()
                .withInDate(now)
                .withSessionId(sessionCode)
                .withMemberIdx(user)
                .withExpireDate(expire)
                .build();
        // 로그인 기록을 저장 한뒤 응답
        repositoryAuth.save(memberAccess);
        user.setAcceptAt(LocalDateTime.now());
        return response.makeSuccessResponse(ResTokenDTO.builder().token(token).build());
    }

    /**
     * 로그아웃
     * @param token
     * @return
     */
    @Transactional
    public ResponseEntity signOut(String token) {
        TokenDTO tokenData = jwt.valid(token);
        Claims data = tokenData.getData();
        if( data == null ){
            log.error(tokenData.getMsg());
            return response.makeOtherResponse(HttpStatus.UNAUTHORIZED);
        }
        log.debug("claims : "+data.toString());
        MemberAccess memberAccess = repositoryAuth.findBySessionId(data.get("session").toString());
        memberAccess.setOutDate(LocalDateTime.now());
        return response.makeSuccessResponse(HttpStatus.OK);
    }
}
