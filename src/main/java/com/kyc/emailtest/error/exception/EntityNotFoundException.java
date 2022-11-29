package com.kyc.emailtest.error.exception;

import com.kyc.emailtest.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{

        public EntityNotFoundException(String message) {
            super(message, ErrorCode.ENTITY_NOT_FOUND);
        }

        public EntityNotFoundException(String value, ErrorCode errorCode) {
            super(value, errorCode);
        }
}
