package org.catty.account;

import java.util.List;
import java.util.Map;
import org.catty.account.domain.Account;

public interface MemberService {
	public boolean joinMember(JoinInfo joinInfo);
	public boolean checkIdDuplication(String id);
	public List<Account> getMemberList(String term);
	public Account getMember(Account member);
	public boolean editMember(JoinInfo joinInfo);
	public boolean withdrawal(Account member);
	public Map<String, Object> getFacilityInChargeList(Account member);
}
