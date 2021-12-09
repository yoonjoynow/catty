package org.catty.domains.account.dao;

import java.util.Optional;
import org.catty.domains.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByEmail(String email);

    Optional<Account> findByUsername(String username);

    boolean existsByEmail(String email);
}
