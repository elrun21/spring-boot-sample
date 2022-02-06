package com.example.sample.repository;

import com.example.sample.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long > , MemberCustomRepository {
    Member findByIdAndPassword(String id, String password);
    Member findByEmailAndPassword(String email, String password);
    Member findByIdx(Long idx );
}
