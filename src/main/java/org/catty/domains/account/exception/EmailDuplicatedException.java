package org.catty.domains.account.exception;

import org.catty.global.error.ErrorCode;
import org.catty.global.error.exception.DuplicatedValueException;

public class EmailDuplicatedException extends DuplicatedValueException {

    public EmailDuplicatedException(String message) {
        super(message);
    }

    public EmailDuplicatedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
