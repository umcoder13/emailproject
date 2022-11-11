package com.kyc.emailtest.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EmailUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String authCode;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    public static EmailUser createEmailUser (String email, String authCode) {
        EmailUser user = new EmailUser();
        user.email = email;
        user.authCode = authCode;

        return user;
    }
}
