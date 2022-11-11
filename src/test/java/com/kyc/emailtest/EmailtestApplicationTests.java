package com.kyc.emailtest;

import com.kyc.emailtest.entity.EmailUser;
import com.kyc.emailtest.repository.EmailRepository;
import com.kyc.emailtest.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class EmailtestApplicationTests {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    EmailService emailService;



    /*
    @Test
    void h2OK() {
        String email = "test@test.com";
        String code = "test12";

        EmailUser user = EmailUser.createEmailUser(email, code);

        emailRepository.save(user);

        EmailUser findEmailUser = emailRepository.findById(user.getId()).get();
        Assertions.assertEquals(user.getEmail(), findEmailUser.getEmail());

    }

     */

    @Test
    void serviceTest() {
        String email = "test@test.com";
        String code = "test12";

        EmailUser user = EmailUser.createEmailUser(email, code);

        emailRepository.save(user);

        boolean testCaseAllTrue = emailService.verifyEmailCode(email, code, LocalDateTime.now());
        boolean testCaseEmailFalse = emailService.verifyEmailCode("test12@test.com", code, LocalDateTime.now());
        boolean testCaseCodeFalse = emailService.verifyEmailCode(email, "test13", LocalDateTime.now());
        boolean testCaseTimeFalse = emailService.verifyEmailCode(email, code, LocalDateTime.now().plusSeconds(601));

        Assertions.assertTrue(testCaseAllTrue);
        Assertions.assertFalse(testCaseEmailFalse);
        Assertions.assertFalse(testCaseCodeFalse);
        Assertions.assertFalse(testCaseTimeFalse);
    }

}
