package org.catty.account.domain;

import java.time.LocalDateTime;
import org.catty.account.auth.AccountRole;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void createAccount() {
        Account account = Account.builder()
                .email("hello@gmail.com")
                .password("12345678")
                .username("yoon")
                .phoneNumber("010-7453-4525")
                .build();

        assertThat(account.getRole()).isEqualTo(AccountRole.USER);
    }

}