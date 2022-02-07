package com.example.sample.repository;
import com.example.sample.domain.entity.Member;
import com.example.sample.domain.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Member findUserOne(String id) {
         return  queryFactory.selectFrom(QMember.member)
                .where(QMember.member.id.eq(id))
                .fetchOne();

    }
}
