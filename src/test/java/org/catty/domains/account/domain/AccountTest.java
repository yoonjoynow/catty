package org.catty.domains.account.domain;

import org.catty.domains.account.model.AccountRole;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

//    docker run -p 3306:3306 --name catty -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=catty_test -e MYSQL_USER=yoon -e MYSQL_PASSWORD=pass -d --platform linux/amd64 mysql

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