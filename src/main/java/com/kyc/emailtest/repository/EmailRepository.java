package com.kyc.emailtest.repository;

import com.kyc.emailtest.entity.EmailUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailUser, Long> {
}
