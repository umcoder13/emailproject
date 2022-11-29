package com.kyc.emailtest.error;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    INVALID_QUERY_VALUE(400, "C002", "Invalid Query Value"),
    ENTITY_NOT_FOUND(404, "C001", "Entity not Found"),

    EMAIL_DUPLICATION(401, "M001", "Email is Already Signed Up"),
    EMAIL_NOT_VERIFIED(404, "M002", "Email is not Verified"),

    EMAIL_HISTORY_NOT_EXIST(404, "M003", "Email History is not Exist"),
    AUTH_CODE_NOT_MATCH(401, "A001", "Authentication Code does not Match"),
    AUTH_CODE_EXPIRED(401, "A002", "Authentication Code has Expired");

    private final String code;

    private final String message;

    private final int status;


    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() { return this.message; }
    public String getCode() { return this.code; }
    public int getStatus() { return this.status; }
}
