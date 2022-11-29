package com.kyc.emailtest.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class EmailRequestDto {
    @NotNull
    @NotEmpty
    private String code;
}
