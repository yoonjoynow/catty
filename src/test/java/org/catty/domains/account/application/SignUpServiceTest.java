package org.catty.domains.account.application;

import org.catty.domains.account.dao.AccountRepository;
import org.catty.domains.account.domain.Account;
import org.catty.domains.account.dto.SignUpRequest;
import org.catty.domains.account.exception.EmailDuplicatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SignUpServiceTest {

    /**
     *  1. 회원 가입 성공
     *  2. 회원 가입 실패
     *  3. 이메일 중복
     */

    @InjectMocks
    SignUpService signUpService;

    @Mock
    AccountRepository accountRepository;

    Account account;

    SignUpRequest dto;

    @BeforeEach
    void setUp() {
        this.dto = SignUpRequest.builder()
                .email("yoon@gmail.com")
                .password("1234")
                .username("yoon")
                .phoneNumber("010-1234-5678")
                .build();

        this.account = this.dto.toEntity();
    }

    @Test
    void serviceIsNotNull() {
        assertThat(this.signUpService).isNotNull();
    }

    @Test
    @DisplayName("이메일이 중복되는 테스트")
    void emailDuplicated() {
        //given
        given(this.accountRepository.existsByEmail(anyString()))
                .willReturn(true);

        //when
        Exception expected = assertThrows(EmailDuplicatedException.class, () -> this.signUpService.signUp(this.dto));

        //then
        assertThat(expected).isInstanceOf(EmailDuplicatedException.class);
    }

    @Test
    @DisplayName("이메일이 중복되지 않는 테스트")
    void emailNotDuplicated() {
        //given
        given(this.accountRepository.existsByEmail(anyString()))
                .willReturn(false);
        given(this.accountRepository.save(any(Account.class)))
                .willReturn(this.account);

        //TODO id가 null인데 값이 존재하도록 바꿀 수 있을까?
        //when
        Integer savedId = this.signUpService.signUp(dto);

        //then
        assertThat(savedId).isEqualTo(this.account.getId());
    }

    @Test
    @DisplayName("회원 가입 성공 테스트")
    void signUp() {
        //given
        given(this.accountRepository.existsByEmail(anyString()))
                .willReturn(false);
        given(this.accountRepository.save(any(Account.class)))
                .willReturn(this.account);

        //when
        Integer accountId = this.signUpService.signUp(this.dto);

        //then
        assertThat(accountId).isEqualTo(this.account.getId());
    }

}