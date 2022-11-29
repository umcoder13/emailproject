package com.kyc.emailtest.service;

import com.kyc.emailtest.error.ErrorCode;
import com.kyc.emailtest.error.exception.InvalidValueException;

public class AuthCodeNotMatchException extends InvalidValueException {
    public AuthCodeNotMatchException(String email) {
        super(email + "'s auth code does not match the db's code", ErrorCode.AUTH_CODE_NOT_MATCH);
    }
}
