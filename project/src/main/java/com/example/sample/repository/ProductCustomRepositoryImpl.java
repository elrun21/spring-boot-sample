package com.example.sample.repository;
import com.example.sample.domain.dto.response.ResMemberInfosDTO;
import com.example.sample.domain.dto.response.ResProductDTO;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.sample.domain.entity.QProductInfo.productInfo;


@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<ResProductDTO> findProduct(Long targetIdx, String name, int size) {

        return queryFactory.select(
                        Projections.constructor(
                                ResProductDTO.class,
                                productInfo.idx.as("productIdx"),
                                productInfo.salePrice.as("salePrice"),
                                productInfo.originPrice.as("providePrice"),
                                productInfo.productName.as("productName"),
                                productInfo.productType.as("productType"),
                                productInfo.category.as("category"),
                                productInfo.eventNum.as("eventIdx"),
                                productInfo.createAt.as("regDate"),
                                productInfo.updateAt.as("modifyDate")
                        )
                )
                .from(productInfo)
                .where(
                        ltId(targetIdx) , likeName(name)
                )
                .orderBy(  productInfo.idx.desc()  )
                .limit(size)
                .fetch();
    }
    private BooleanExpression ltId(Long idx) {
        if (idx == null || idx == 0 ) {
            return null; // BooleanExpression 자리에 null이 반환되면 조건문에서 자동으로 제거된다
        }
        return productInfo.idx.lt(idx);
    }

    private BooleanExpression likeName(String name) {
        if(name == null ) return null;
       return productInfo.productName.like(name + "%");
    }

}
