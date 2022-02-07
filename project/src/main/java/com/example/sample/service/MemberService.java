package com.example.sample.service;

import com.example.sample.common.dto.TokenDTO;
import com.example.sample.common.enums.MemberEnum;
import com.example.sample.common.exceptions.TokenValidationCustomException;
import com.example.sample.common.exceptions.TokenValidationIdException;
import com.example.sample.common.utils.JwtUtils;
import com.example.sample.common.utils.LogUtils;
import com.example.sample.common.utils.ResponseUtils;
import com.example.sample.common.utils.SecurityUtils;
import com.example.sample.domain.dto.request.ReqMemberModifyDTO;
import com.example.sample.domain.dto.response.ResMemberInfoDTO;
import com.example.sample.domain.dto.response.ResMemberInfosDTO;
import com.example.sample.domain.dto.request.ReqSignUpMembeDTO;
import com.example.sample.domain.entity.Member;
import com.example.sample.domain.entity.MemberInfo;
import com.example.sample.repository.AuthRepository;
import com.example.sample.repository.MemberInfoRepository;
import com.example.sample.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {
    private final JwtUtils jwt ;
    private final MemberRepository repositoryMember;
    private final MemberInfoRepository repositoryMemberInfo;
    private final AuthRepository repositoryAuth;
    private final ResponseUtils response;
    private final LogUtils logUtils;
    private final SecurityUtils security;


    /**
     * 한 사용자만 조회
     * @param idx
     * @return
     */
    public ResponseEntity findUserOne(Long idx ) {
        // 사용자 정보 조회
        MemberInfo memberInfo = repositoryMemberInfo.findMemberInfoByMemberIdx(
                Member.MemberBuilder.aMember().withIdx(idx).build()
        );
        if( memberInfo == null )  return response.makeOtherResponse(HttpStatus.NO_CONTENT);
        Member member = memberInfo.getMemberIdx();
        // 전달할 객체 세팅 후 전달
        ResMemberInfoDTO result = ResMemberInfoDTO.builder()
                .userGrade(member.getUserGrade())
                .userId(member.getId())
                .userAccountStatus(member.getLiveStatus())
                .userAddr(memberInfo.getUserAddr())
                .userName(memberInfo.getUserName())
                .userPhone(memberInfo.getUserPhone())
                .userJoinDate(member.getCreateAt())
                .build();
        return response.makeSuccessResponse( result);
    }

    /**
     * 유저 리스트 조회 ( cursor 페이징 )
     * @param lastIdx
     * @param prevIdx
     * @param limit
     * @return
     */
    public ResponseEntity findUsers( Long lastIdx , Long prevIdx, int limit ) {
        if( limit == 0 ) return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        if( lastIdx != 0 && prevIdx !=0 ) response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        if ( lastIdx !=0 ){
            return response.makeSuccessResponse( repositoryMemberInfo.findAllMembers( lastIdx , true , limit ) );
        }else{
            return response.makeSuccessResponse( repositoryMemberInfo.findAllMembers( prevIdx , false , limit ) );
        }
    }

    /**
     * 회원 가입 처리
     * @param data
     * @return
     * @throws Exception
     */
    @Validated
    @Transactional
    public ResponseEntity signUp(ReqSignUpMembeDTO data) throws Exception{
        try {
            /**
             * 기본 회원 테이블 저장
             */
            Member newMember = repositoryMember.save(Member.MemberBuilder.aMember()
                    .withId(data.getId())
                    .withPassword( security.getAESEncrypt(data.getPassword()) )
                    .withLiveStatus("LIVE")
                    .withUserGrade(1)
                    .withEmail(data.getEmail())
                    .withDeleteAt(null)
                    .build()
            );
            /**
             * 기본회원테이블 데이터 키를 기준으로 상세 내용 저장
             */
            if (newMember == null)  return response.makeOtherResponse(HttpStatus.BAD_REQUEST, "member is not create", 201);
            MemberInfo newMemberInfo = repositoryMemberInfo.save(MemberInfo.MemberInfoBuilder.aMemberInfo()
                    .withMemberIdx(newMember)
                    .withUserAddr(data.getAddr())
                    .withUserName(data.getName())
                    .withUserPhone(data.getPhone())
                    .build()
            );
            if (newMemberInfo == null) return response.makeOtherResponse(HttpStatus.BAD_REQUEST, "member info is not create", 202);
            return response.makeSuccessResponse(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            log.error(logUtils.getErrorLog(e));
            // Rollback 위한 RuntimeException 발생
            throw new RuntimeException("MemberCreateException");
        }
    }


    /**
     * 회원 탈퇴
     * @param token
     * @return
     */
    @Transactional
    public ResponseEntity deleteUser( String token , String id ) {
        // 토큰에서 데이터 추출
        if( token == null  ) return response.makeOtherResponse(HttpStatus.UNAUTHORIZED);
        TokenDTO tokenData = jwt.valid(token);
        if ( tokenData.getStatus() != HttpStatus.OK ){
            log.error("status : "+tokenData.getStatus());
            return response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        }
        log.debug("token data : "+tokenData.toString());
        Long idx = getIdx(tokenData.getData(), id );
        // 탈퇴할 사용자 정보 조회
        Member member = repositoryMember.findByIdx(idx);
        if( member == null )
        // 탈퇴 상태값 변경
        member.setLiveStatus(MemberEnum.USER_STATE_DELETE.getCode());
        member.setDeleteAt(LocalDateTime.now());
        return response.makeOtherResponse(HttpStatus.NO_CONTENT);
    }

    /**
     * 회원 상세 정보를 수정 하는 역할
     * @param data
     * @param token
     * @return
     */
    @Validated
    @Transactional
    public ResponseEntity updateMemberInfo(ReqMemberModifyDTO data , String token) {
        if( data.getAddr() == null && data.getPhone() == null ) return  response.makeOtherResponse(HttpStatus.BAD_REQUEST);
        TokenDTO tokenData = jwt.valid(token);
        Long idx = getIdx(tokenData.getData(), data.getId());
        Member member = repositoryMember.findByIdx(idx);
        MemberInfo info = repositoryMemberInfo.findMemberInfoByMemberIdx(member);
        if( info.getUserAddr() != null ) info.setUserAddr(data.getAddr());
        if( info.getUserPhone() !=null ) info.setUserPhone(data.getPhone());
        return response.makeOtherResponse(HttpStatus.NO_CONTENT);
    }

    /**
     * 토큰에서 idx 값을 추출 / 토큰 검증 하는 역할
     * @param token
     * @param id
     * @return
     */
    private Long getIdx( Claims token , String id ){
        String tokenInId = (String) token.get("id");
        if( tokenInId == null ) {
            log.error(token.toString());
            log.error( "token id is null :"+ tokenInId);
            throw new TokenValidationCustomException();
        }
        if( !id.equals(tokenInId) ) throw new TokenValidationIdException();
        return Long.parseLong( String.valueOf(token.get("idx")) );
    }
}
