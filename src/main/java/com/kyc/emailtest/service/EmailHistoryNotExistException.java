package com.kyc.emailtest.service;

import com.kyc.emailtest.error.ErrorCode;
import com.kyc.emailtest.error.exception.EntityNotFoundException;

public class EmailHistoryNotExistException extends EntityNotFoundException {
    public EmailHistoryNotExistException(String email) {
        super(email + "'s history is not exist", ErrorCode.EMAIL_HISTORY_NOT_EXIST);
    }
}
