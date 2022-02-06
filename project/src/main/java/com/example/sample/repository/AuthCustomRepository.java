package com.example.sample.repository;

import com.example.sample.domain.entity.Member;

public interface AuthCustomRepository {
    Member findUserOne(String id) ;
}
