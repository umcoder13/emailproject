package com.kyc.emailtest.controller;

import com.kyc.emailtest.dto.EmailRequestDto;
import com.kyc.emailtest.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/{email_addr}/authcode")
    public ResponseEntity<String> getEmailPath(@PathVariable String email_addr) throws MessagingException {
        emailService.sendEmail(email_addr);
        return ResponseEntity.ok("이메일을 확인하세요");
    }

    @PostMapping("/{email_addr}/authcode")
    public ResponseEntity<String> getAndVerifyEmailAndCode(@PathVariable String email_addr, @RequestBody EmailRequestDto dto) throws NoSuchAlgorithmException {
        if (emailService.verifyEmailCode(email_addr, dto.getCode())) {
            return ResponseEntity.ok(emailService.makeMemberId(email_addr));
        }
        return ResponseEntity.notFound().build();
    }
}
