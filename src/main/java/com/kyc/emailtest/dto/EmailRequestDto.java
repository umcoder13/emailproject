package com.kyc.emailtest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class EmailRequestDto {
    private String code;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime time;


    @Builder
    public EmailRequestDto(String code, LocalDateTime time) {
        this.code = code;
        this.time = time;
    }
}


