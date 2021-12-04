package org.catty.account.dto;

import lombok.Builder;
import lombok.Getter;
import org.catty.account.domain.Account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SignUpRequest {

    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
    @NotBlank
    private String phoneNumber;

    @Builder
    public SignUpRequest(String email, String password, String username, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public Account toEntity() {
        return Account.builder()
                .email(this.email)
                .password(this.password)
                .username(this.username)
                .phoneNumber(this.phoneNumber)
                .build();
    }
}
