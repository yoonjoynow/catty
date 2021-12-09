package org.catty.domains.account.dao;

import lombok.RequiredArgsConstructor;
import org.catty.domains.account.domain.Account;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AccountFetchDao {

    private final DSLContext context;

}
