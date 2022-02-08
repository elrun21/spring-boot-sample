package com.example.sample.repository;

import com.example.sample.domain.dto.response.ResOrderDTO;
import com.example.sample.domain.entity.Member;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.sample.domain.entity.QOrderInfo.orderInfo;


@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<ResOrderDTO> findOrder(Member member, Long targetIdx, String productName, int size) {

        return queryFactory.select(
                        Projections.constructor(
                                ResOrderDTO.class,
                                orderInfo.orderNo.as("orderNumber"),
                                orderInfo.productName.as("productName"),
                                orderInfo.paymentPrice.as("totalPrice"),
                                orderInfo.productCount.as("totalCount"),
                                orderInfo.paymentType.as("paymentType"),
                                orderInfo.createAt.as("orderDate"),
                                orderInfo.userId.as("sender"),
                                orderInfo.receiver.as("receiver"),
                                orderInfo.addr.as("addr")
                        )
                )
                .from(orderInfo)
                .where(
                       orderInfo.userIdx.eq(member) , ltId(targetIdx), likeName(productName)
                )
                .orderBy(  orderInfo.idx.desc()  )
                .limit(size)
                .fetch();
    }
    private BooleanExpression ltId(Long idx) {
        if (idx == null || idx == 0 ) {
            return null; // BooleanExpression 자리에 null이 반환되면 조건문에서 자동으로 제거된다
        }
        return orderInfo.idx.lt(idx);
    }
    private BooleanExpression likeName(String name) {
        if(name == null ) return null;
        return orderInfo.productName.like(name + "%");
    }

}
