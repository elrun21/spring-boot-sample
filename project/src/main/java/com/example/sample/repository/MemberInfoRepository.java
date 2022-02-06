package com.example.sample.repository;

import com.example.sample.domain.entity.Member;
import com.example.sample.domain.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long > , MemberInfoCustomRepository {
     MemberInfo findMemberInfoByMemberIdx(Member memer) ;

}
