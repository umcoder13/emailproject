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

    public void verifyEmailExist(String email) {
        if(memberRepository.existsMemberByEmail(email)) {
            log.error("이미 가입되어 있습니다.");
        }
    }
}
