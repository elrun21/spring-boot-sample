package com.example.sample.repository;

import com.example.sample.domain.dto.response.ResMemberInfosDTO;

import java.util.List;

public interface MemberInfoCustomRepository {
    List<ResMemberInfosDTO> findAllMembers(Long targetIdx ,  int size );
}
