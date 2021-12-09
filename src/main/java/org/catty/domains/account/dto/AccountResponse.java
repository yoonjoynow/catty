package org.catty.domains.account.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.catty.domains.account.domain.Account;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountResponse {

    private String email;
    private String username;
    private String phoneNumber;

    public AccountResponse(Account account) {
        this.email = account.getEmail();
        this.username = account.getUsername();
        this.phoneNumber = account.getPhoneNumber();
    }
}
