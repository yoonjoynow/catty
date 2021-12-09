package org.catty.domains.account.dao;

import java.util.Optional;
import org.catty.domains.account.domain.Account;
import org.catty.domains.account.dto.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    Account account;

    @BeforeEach
    void setUp() {
        Account savedAccount = Account.builder()
                .email("hello1234@gmail.com")
                .password("1234")
                .username("yoon")
                .phoneNumber("010-1234-5678")
                .build();

        this.account = this.accountRepository.save(savedAccount);
    }

    @Test
    @DisplayName("회원 정보 저장 테스트")
    void saveAccount() {
        //arrange
        String email = "hello@gmail.com";
        SignUpRequest dto = SignUpRequest.builder()
                .email(email)
                .password("1234")
                .phoneNumber("010-1234-5678")
                .build();

        //act
        Account savedAccount = this.accountRepository.save(dto.toEntity());
        Account findAccount = this.accountRepository.findById(savedAccount.getId()).orElseThrow();

        //assert
        assertThat(savedAccount).isEqualTo(findAccount);
    }

    @Test
    @DisplayName("회원 조회 성공 테스트")
    void findAccount() {
        //arrange
        String email = "hello@gmail.com";
        SignUpRequest dto = SignUpRequest.builder()
                .email(email)
                .password("1234")
                .username("James")
                .phoneNumber("010-7453-4525")
                .build();
        Account savedAccount = this.accountRepository.save(dto.toEntity());

        //act
        Account findAccount = this.accountRepository.findById(savedAccount.getId()).orElseThrow();

        //assert
        assertThat(savedAccount.getId()).isEqualTo(findAccount.getId());
    }

    @Test
    @DisplayName("없는 회원 조회 테스트")
    void findAccount_throwsAccountNotFoundException() {
        Optional<Account> account = this.accountRepository.findById(1000);
        assertThat(account.isPresent()).isFalse();
    }

    @Test
    @DisplayName("이메일 조회 테스트")
    void findByEmail() {
        Optional<Account> account = this.accountRepository.findByEmail(this.account.getEmail());
        assertThat(account).isPresent();
    }

}