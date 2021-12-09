package org.catty.global.error.exception;

import org.catty.global.error.ErrorCode;

public class InvalidInputValueException extends BusinessException {

    public InvalidInputValueException(String message) {
        super(message, ErrorCode.INVALID_INPUT_VALUE);
    }

    public InvalidInputValueException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
