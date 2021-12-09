package org.catty.domains.account.application;

import java.util.Optional;
import org.catty.domains.account.dao.AccountRepository;
import org.catty.domains.account.domain.Account;
import org.catty.domains.account.dto.AccountResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    Account account;

    @Test
    void setUP() {

    }

    @Test
    @DisplayName("회원 정보 조회 성공 테스트")
    void findMemberById_pass() {
        //given
        given(this.accountRepository.findById(anyInt()))
                .willReturn(Optional.of(this.account));

        //when
        AccountResponse account = this.accountService.findAccountById(1);

        //then
        assertThat(account).isNotNull();
    }

}