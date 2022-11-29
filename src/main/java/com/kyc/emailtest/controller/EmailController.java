package com.kyc.emailtest.controller;

import com.kyc.emailtest.dto.EmailRequestDto;
import com.kyc.emailtest.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.security.NoSuchAlgorithmException;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/{email_addr}/authcode")
    public ResponseEntity<String> getEmailPath(@PathVariable @Email String email_addr) throws MessagingException {
        emailService.sendEmail(email_addr);
        return ResponseEntity.ok("이메일을 확인하세요");
    }

    @PostMapping("/{email_addr}/authcode")
    public ResponseEntity<String> getAndVerifyEmailAndCode(@PathVariable @Email String email_addr, @RequestBody @Valid EmailRequestDto dto) throws NoSuchAlgorithmException {
        emailService.verifyEmailCode(email_addr, dto.getCode());
        return ResponseEntity.ok(emailService.makeMemberId(email_addr));

    }
}
