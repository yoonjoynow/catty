package org.catty.account.dto;

import lombok.Builder;
import lombok.Getter;
import org.catty.account.domain.Account;
import org.catty.account.auth.AccountRole;

@Getter
public class SignUpResponse {

    private final String email;
    private final String password;
    private final String username;
    private final String phoneNumber;
    private final AccountRole role;

    @Builder
    public SignUpResponse(Account member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.username = member.getUsername();
        this.phoneNumber = member.getPhoneNumber();
        this.role = member.getRole();
    }
}
