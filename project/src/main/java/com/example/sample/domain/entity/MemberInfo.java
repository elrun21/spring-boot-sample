package com.example.sample.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "MEMBER_INFO")
@Entity
@Getter
@NoArgsConstructor
public class MemberInfo extends UpdateTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ApiModelProperty("사용자 전화번호")
    @Column
    private String userPhone;

    @ApiModelProperty("사용자 이름")
    @Column
    private String userName;

    @ApiModelProperty("사용자 주소")
    @Column
    private String userAddr;

    @ApiModelProperty("(FK) 회원 정보 테이블 키")
    @OneToOne( targetEntity = Member.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="member_idx")
    private Member memberIdx;

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public static final class MemberInfoBuilder {
        private String userPhone;
        private String userName;
        private String userAddr;
        private Member memberIdx;

        private MemberInfoBuilder() {
        }

        public static MemberInfoBuilder aMemberInfo() {
            return new MemberInfoBuilder();
        }

        public MemberInfoBuilder withUserPhone(String userPhone) {
            this.userPhone = userPhone;
            return this;
        }

        public MemberInfoBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public MemberInfoBuilder withUserAddr(String userAddr) {
            this.userAddr = userAddr;
            return this;
        }

        public MemberInfoBuilder withMemberIdx(Member memberIdx) {
            this.memberIdx = memberIdx;
            return this;
        }

        public MemberInfo build() {
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.userPhone = this.userPhone;
            memberInfo.userAddr = this.userAddr;
            memberInfo.memberIdx = this.memberIdx;
            memberInfo.userName = this.userName;
            return memberInfo;
        }
    }
}
