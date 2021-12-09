package org.catty.global.error.exception;

import lombok.Getter;
import org.catty.global.error.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
