package org.catty.account;

import java.util.List;
import org.catty.account.domain.Account;

public interface MemberMapper {
    public int count(Account member) throws Exception;
    public List<Account> selectMemberList(String term) throws Exception;
    public Account selectMember(Account member) throws Exception;
    public int insertMember(Account member) throws Exception;
    public int updateMember(Account member) throws Exception;
    public int deleteMember(Account member) throws Exception;
}