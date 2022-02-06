package com.example.sample.repository;

import com.example.sample.domain.entity.Member;

public interface MemberCustomRepository {
    Member findUserOne(String id) ;
}
