package com.example.sample.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Table(name = "MEMBER")
@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
public class Member extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ApiModelProperty("사용자 아이디")
    @Column(unique = true)
    private String id ;

    @Email
    @ApiModelProperty("사용자 이메일")
    @Column(unique = true)
    private String email ;

    @ApiModelProperty("사용자 비밀번호")
    @Column
    private String password;

    @ApiModelProperty("사용자 계정 상태(LIVE , BLOCK , DELETE)")
    @Column
    private String liveStatus;

    @ApiModelProperty("사용자 등급")
    @Column
    private int userGrade;

    @ApiModelProperty("삭제된 날짜 ")
    @Column
    private LocalDateTime deleteAt ;

    @ApiModelProperty("최근 접속일")
    @Column
    private LocalDateTime acceptAt ;

    public void updateAcceptAt(LocalDateTime now) {
        this.acceptAt = now;
    }

    public void updateLiveStatus(String code) {
        this.liveStatus = code;
    }

    public void updateDeleteAt(LocalDateTime now) {
        this.deleteAt = now ;
    }

    public static final class MemberBuilder {

        private String id ;
        private String email;
        private String password;

        private String liveStatus;
        private int userGrade;
        private LocalDateTime deleteAt;
        private LocalDateTime acceptAt ;
        private MemberBuilder() {
        }

        public static MemberBuilder aMember() {
            return new MemberBuilder();
        }



        public MemberBuilder withId(String id) {
            this.id = id;
            return this;
        }
        public MemberBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public MemberBuilder withPassword(String password) {
            this.password = password;
            return this;
        }



        public MemberBuilder withLiveStatus(String liveStatus) {
            this.liveStatus = liveStatus;
            return this;
        }
        public MemberBuilder withUserGrade(int userGrade) {
            this.userGrade = userGrade;
            return this;
        }
        public MemberBuilder withDeleteAt(LocalDateTime deleteAt) {
            this.deleteAt = deleteAt;
            return this;
        }
        public MemberBuilder withAcceptAt(LocalDateTime acceptAt) {
            this.acceptAt = acceptAt;
            return this;
        }


        public Member build() {
            Member member = new Member();
            member.id = this.id;
            member.email=this.email;
            member.password = this.password;
            member.liveStatus = this.liveStatus;
            member.userGrade = this.userGrade;
            member.deleteAt = this.deleteAt;
            member.acceptAt = this.acceptAt;
            return member;
        }
    }
}
