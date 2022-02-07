package com.example.sample.repository;
import com.example.sample.domain.dto.response.ResMemberInfosDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<ResMemberInfosDTO> findProduct(Long targetIdx, boolean direction, int size) {
        return null;
    }
}
