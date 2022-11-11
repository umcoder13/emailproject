package com.kyc.emailtest;

import com.kyc.emailtest.entity.EmailUser;
import com.kyc.emailtest.repository.EmailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailtestApplicationTests {

    @Autowired
    EmailRepository emailRepository;



    @Test
    void h2OK() {
        String email = "test@test.com";
        String code = "test12";

        EmailUser user = EmailUser.createEmailUser(email, code);

        emailRepository.save(user);

        EmailUser findEmailUser = emailRepository.findById(user.getId()).get();
        Assertions.assertEquals(user.getEmail(), findEmailUser.getEmail());

    }

}
