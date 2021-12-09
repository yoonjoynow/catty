package org.catty.domains.account.exception;

import org.catty.global.error.ErrorCode;
import org.catty.global.error.exception.EntityNotFoundException;

public class AccountNotFoundException extends EntityNotFoundException {

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
