package com.kyc.emailtest.service;

import com.kyc.emailtest.error.ErrorCode;
import com.kyc.emailtest.error.exception.BusinessException;

public class EmailDuplicationException extends BusinessException {
    public EmailDuplicationException(String email) {
        super(email + "is duplicated", ErrorCode.EMAIL_DUPLICATION);
    }
}
