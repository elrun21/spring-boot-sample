package com.example.sample.repository;

import com.example.sample.domain.dto.response.ResMemberInfosDTO;

import java.util.List;

public interface ProductCustomRepository {
    List<ResMemberInfosDTO> findProduct(Long targetIdx , boolean direction , int size );
}
