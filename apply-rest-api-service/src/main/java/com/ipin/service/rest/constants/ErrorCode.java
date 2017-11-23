package com.ipin.service.rest.constants;

/**
 * Created by longman on 1/12/16.
 */
public enum ErrorCode {
    SYSTEM_ERROR(1001, "System error"),
    SERVICE_UNAVAILABLE(1002, "Service unavailable"),
    PARAM_REQUIRE(2001, "Param is require"),
    PARAM_ILLEGAL(2002, "Param is illegal"),
    API_NOT_FOUND(2003, "Request api is not found"),
    HTTP_METHOD_ERROR(2004, "HTTP method is not suported for this request"),
    UNKNOWN_ERROR(1000, "Unknown error.");

    private final int code;
    private final String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}