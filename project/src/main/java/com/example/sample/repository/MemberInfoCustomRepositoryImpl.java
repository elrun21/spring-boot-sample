package com.example.sample.repository;

import com.example.sample.domain.dto.response.ResMemberInfosDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


import java.util.List;

import static com.example.sample.domain.entity.QMember.*;
import static com.example.sample.domain.entity.QMemberInfo.memberInfo;

@RequiredArgsConstructor
public class MemberInfoCustomRepositoryImpl implements MemberInfoCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ResMemberInfosDTO> findAllMembers(Long targetIdx, int size) {
        return queryFactory.select(
                    Projections.constructor(
                            ResMemberInfosDTO.class,
                            member.idx.as("userIdx"),
                            member.id.as("userId"),
                            memberInfo.userName.as("userName"),
                            memberInfo.userPhone.as("userPhone"),
                            memberInfo.userAddr.as("userAddr"),
                            member.userGrade.as("userGrade"),
                            member.liveStatus.as("userAccountStatus"),
                            member.createAt.as("userJoinDate")
                    )
                )
                .from(memberInfo)
                .join(memberInfo.memberIdx, member)
                .where(
                        ltId(targetIdx)
                )
                .orderBy(memberInfo.idx.desc())
                .limit(size)
                .fetch();

    }
    private BooleanExpression ltId(Long idx) {
        if (idx == null || idx == 0 ) {
            return null; // BooleanExpression 자리에 null이 반환되면 조건문에서 자동으로 제거된다
        }else {
            return memberInfo.idx.lt(idx);
        }
    }
}
