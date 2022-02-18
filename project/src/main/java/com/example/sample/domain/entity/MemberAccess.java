package com.example.sample.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "MEMBER_ACCESS")
@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
public class MemberAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ApiModelProperty("세션키")
    @Column
    private String sessionId;

    @ApiModelProperty("접속일시")
    @Column
    private LocalDateTime inDate;

    @ApiModelProperty("접속 종료일시")
    @Column
    private LocalDateTime outDate;

    @ApiModelProperty("세션 만료 예정 시간")
    @Column
    private LocalDateTime expireDate;

    @ApiModelProperty("(FK) 회원정보 테이블 키")
    @ManyToOne( targetEntity = Member.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="member_idx")
    private Member memberIdx;

    public void updateOutDate(LocalDateTime now) {
        this.outDate = now;
    }

    public static final class MemberAccessBuilder {
        private String sessionId;
        private LocalDateTime inDate;
        private LocalDateTime outDate;
        private LocalDateTime expireDate;
        private Member memberIdx;

        private MemberAccessBuilder() {
        }

        public static MemberAccessBuilder aMemberAccess() {
            return new MemberAccessBuilder();
        }

        public MemberAccessBuilder withSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public MemberAccessBuilder withExpireDate(LocalDateTime expireDate) {
            this.expireDate = expireDate;
            return this;
        }

        public MemberAccessBuilder withInDate(LocalDateTime inDate) {
            this.inDate = inDate;
            return this;
        }

        public MemberAccessBuilder withOutDate(LocalDateTime outDate) {
            this.outDate = outDate;
            return this;
        }

        public MemberAccessBuilder withMemberIdx(Member memberIdx) {
            this.memberIdx = memberIdx;
            return this;
        }

        public MemberAccess build() {
            MemberAccess memberAccess = new MemberAccess();
            memberAccess.outDate = this.outDate;
            memberAccess.sessionId = this.sessionId;
            memberAccess.memberIdx = this.memberIdx;
            memberAccess.inDate = this.inDate;
            memberAccess.expireDate = this.expireDate;
            return memberAccess;
        }
    }
}
