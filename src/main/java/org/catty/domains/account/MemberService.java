package org.catty.domains.account;

import java.util.List;
import java.util.Map;
import org.catty.domains.account.domain.Account;
import org.catty.domains.account.dto.SignUpRequest;

public interface MemberService {
	public boolean joinMember(SignUpRequest signUpRequest);
	public boolean checkIdDuplication(String id);
	public List<Account> getMemberList(String term);
	public Account getMember(Account member);
	public boolean editMember(SignUpRequest signUpRequest);
	public boolean withdrawal(Account member);
	public Map<String, Object> getFacilityInChargeList(Account member);
}
