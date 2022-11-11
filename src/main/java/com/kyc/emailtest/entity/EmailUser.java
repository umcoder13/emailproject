package com.kyc.emailtest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
public class EmailUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String authCode;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss:SSS", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now().withNano(0);

    public static EmailUser createEmailUser (String email, String authCode) {
        EmailUser user = new EmailUser();
        user.email = email;
        user.authCode = authCode;

        return user;
    }
}
