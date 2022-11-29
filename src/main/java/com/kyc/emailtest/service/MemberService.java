package com.kyc.emailtest.service;

import com.kyc.emailtest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void verifyEmailAlreadySignedUp(String email) {
        if(memberRepository.existsMemberByEmail(email)) {
            throw new EmailAlreadySignedUpException(email);
        }
    }
}
