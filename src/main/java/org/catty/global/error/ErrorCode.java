package org.catty.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C01", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C02", "Method Not Allowed"),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "C03", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C04", "Server Error"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C05", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "006", "Access is Denied"),
    INPUT_VALUE_DUPLICATED(HttpStatus.BAD_REQUEST, "c007", "Input Value Duplicated"),

    // Account
    EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "A01", "Email is Duplicated"),
    LOGIN_INPUT_INVALID(HttpStatus.BAD_REQUEST, "A02", "Login input is invalid");

    private HttpStatus httpStatus;
    private String code;
    private String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
