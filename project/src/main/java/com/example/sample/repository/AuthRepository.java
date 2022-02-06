package com.example.sample.repository;

import com.example.sample.domain.entity.MemberAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<MemberAccess, Long > , AuthCustomRepository {
    MemberAccess findBySessionId(String session);
}
