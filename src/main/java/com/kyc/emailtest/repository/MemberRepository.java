package com.kyc.emailtest.repository;

import com.kyc.emailtest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsMemberByEmail(String email);
}
