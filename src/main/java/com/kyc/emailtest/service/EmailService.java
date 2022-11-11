package com.kyc.emailtest.service;

import com.kyc.emailtest.entity.EmailUser;
import com.kyc.emailtest.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailRepository emailRepository;
    @Value("${spring.mail.username}")
    private String configEmail;

    // 난수 생성
    private String createdCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <=57 || i >=65) && (i <= 90 || i>= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    // 타임리프 template 생성
    private String setContext(String code) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("code", code);


        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process("mail", context);
    }


    // 메일 반환
    private MimeMessage createEmailForm(String email) throws MessagingException {
        String authCode = createdCode();

        MimeMessage message = mailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("안녕하세요 인증번호입니다.");
        message.setFrom(configEmail);
        message.setText(setContext(authCode), "utf-8", "html");

        emailRepository.save(EmailUser.createEmailUser(email, authCode));
        return message;
    }

    // 메일 보내기
    public void sendEmail(String toEmail) throws MessagingException {
        MimeMessage emailForm = createEmailForm(toEmail);

        mailSender.send(emailForm);
    }

    // 메일 검증
    public boolean verifyEmailCode(String email, String code, LocalDateTime time) {
        List<EmailUser> emailUsers = emailRepository.findAllByEmail(email);

        if(emailUsers.size() == 0) {
            return false;
        }

        EmailUser latelyUser = emailUsers.get(emailUsers.size() - 1);
        long seconds = Duration.between(latelyUser.getCreatedAt(), time).getSeconds();
        log.info("시간의 차이는? " + seconds);
        if(seconds > 600L) {
            return false;
        }


        log.info("메일, 시간 통과");
        return latelyUser.getAuthCode().equals(code);

    }

}
