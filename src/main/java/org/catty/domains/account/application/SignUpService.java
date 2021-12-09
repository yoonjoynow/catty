package org.catty.domains.account.application;

import lombok.RequiredArgsConstructor;
import org.catty.domains.account.dao.AccountRepository;
import org.catty.domains.account.dto.SignUpRequest;
import org.catty.domains.account.exception.EmailDuplicatedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SignUpService {

    private final AccountRepository accountRepository;

    public Integer signUp(SignUpRequest dto) {
        validateEmail(dto);
        return this.accountRepository.save(dto.toEntity()).getId();
    }

    private void validateEmail(SignUpRequest dto) {
        boolean isExist = this.accountRepository.existsByEmail(dto.getEmail());
        if (isExist) {
            throw new EmailDuplicatedException("이메일 [" + dto.getEmail() + "]은 중복됩니다,");
        }
    }

}
