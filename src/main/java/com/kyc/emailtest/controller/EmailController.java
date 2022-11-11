package com.kyc.emailtest.controller;

import com.kyc.emailtest.dto.EmailRequestDto;
import com.kyc.emailtest.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/{email_addr}/authcode")
    public ResponseEntity<String> sendEmailPath(@PathVariable String email_addr) throws MessagingException {
        emailService.sendEmail(email_addr);
        return ResponseEntity.ok("이메일을 확인하세요");
    }

    @PostMapping("/{email_addr}/authcode")
    public ResponseEntity<String> sendEmailAndCode(@PathVariable String email_addr, @RequestBody EmailRequestDto dto) {
        System.out.println(dto.getCode());
        return emailService.verifyEmailCode(email_addr, dto.getCode()) ? ResponseEntity.ok("인증성공!") : ResponseEntity.notFound().build();
    }
}
