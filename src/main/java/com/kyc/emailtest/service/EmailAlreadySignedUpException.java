package com.kyc.emailtest.service;

import com.kyc.emailtest.error.ErrorCode;
import com.kyc.emailtest.error.exception.BusinessException;

public class EmailAlreadySignedUpException extends BusinessException {
    public EmailAlreadySignedUpException(String email) {
        super(email + "is already signed up", ErrorCode.EMAIL_DUPLICATION);
    }
}
