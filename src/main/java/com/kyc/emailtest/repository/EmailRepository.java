package com.kyc.emailtest.repository;

import com.kyc.emailtest.entity.EmailUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<EmailUser, Long> {
    List<EmailUser> findAllByEmail(String email);
}
