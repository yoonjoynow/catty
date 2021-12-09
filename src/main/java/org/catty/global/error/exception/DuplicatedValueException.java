package org.catty.global.error.exception;

import org.catty.global.error.ErrorCode;

public class DuplicatedValueException extends BusinessException {

    public DuplicatedValueException(String message) {
        super(message, ErrorCode.INPUT_VALUE_DUPLICATED);
    }

    public DuplicatedValueException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}
