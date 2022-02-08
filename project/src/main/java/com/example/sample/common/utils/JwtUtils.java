package com.example.sample.common.utils;

import com.example.sample.common.config.SecurityProperties;
import com.example.sample.common.dto.TokenDTO;
import com.example.sample.domain.entity.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
@Slf4j
@Component
public class JwtUtils {
    private String TOKEN_PREFIX = "Bearer ";
    private final SecurityProperties securityProperties;

    public JwtUtils(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    /**
     * 토큰 생성
     * @param member
     * @param session
     * @param now
     * @param expire
     * @return
     */
    public String createToken(Member member, String  session, LocalDateTime now, LocalDateTime expire ) {
        JwtBuilder jwt = Jwts.builder()
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expire.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(Keys.hmacShaKeyFor(securityProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .claim("auth", securityProperties.getAuthKey())
                .claim("idx",member.getIdx())
                .claim("id",member.getId())
                .claim("session" , session);
        return jwt.compact();
    }

    /**
     * 토큰 검증및 데이터 추출
     * @param token
     * @return
     */
    public TokenDTO valid( String token ) {
        try {
            String newToken = deletePrefix(token);
            // validation && get token info
            Claims tokenData =  Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(securityProperties.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(newToken)
                    .getBody();
            log.debug("token: $newToken");
            return TokenDTO.builder()
                    .data(tokenData)
                    .status(HttpStatus.OK)
                    .msg("success")
                    .build();
        } catch ( SecurityException e) {
            return TokenDTO.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .msg("JWT signature does not match locally computed signature.")
                    .build();
        } catch (MalformedJwtException e ) {
            return TokenDTO.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .msg("Invalid JWT Token")
                    .build();
        } catch (ExpiredJwtException e ) {
            return TokenDTO.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .msg("Expired JWT Token")
                    .build();
        } catch (UnsupportedJwtException e ) {
            return TokenDTO.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .msg("Unsupported JWT Token")
                    .build();
        } catch (IllegalArgumentException e ) {
            return TokenDTO.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .msg("JWT Token compact of handler are invalid.")
                    .build();
        }
    }

    public Long getUserIdx(String token){
        TokenDTO claim = valid(token);
        Claims data = claim.getData();
        return Long.parseLong( String.valueOf(data.get("idx")) );
    }

    private String  deletePrefix(String token ) {
        return token.replaceAll(TOKEN_PREFIX,"");
    }
}
