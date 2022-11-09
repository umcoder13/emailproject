package com.kyc.emailtest.controller;

import com.kyc.emailtest.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/{email_addr}/authcode")
    @ResponseBody
    public String sendEmailPath(@PathVariable String email_addr) throws MessagingException, UnsupportedEncodingException {
        emailService.sendEmail(email_addr);
        return "이메일을 확인하세요";
    }

    @PostMapping("/{email_addr}/authcode")
    @ResponseBody
    public String sendEmailAndCode(@PathVariable String email_addr, @RequestBody String code) {
        return emailService.verifyEmailCode(email_addr, code);
    }
}
