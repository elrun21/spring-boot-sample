package com.example.sample.repository;

import com.example.sample.domain.dto.response.ResMemberInfosDTO;
import com.example.sample.domain.dto.response.ResProductDTO;
import com.example.sample.domain.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCustomRepository{
    List<ResProductDTO> findProduct(Long targetIdx , String name, int size );
}
