package org.catty.global.common;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.catty.domains.account.dao.AccountRepository;
import org.catty.domains.account.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

	private final AccountRepository accountRepository;

	@Autowired
	private HttpSession session;

	@Override
	public boolean login(LoginInfo loginInfo) {
		String id = loginInfo.getId();
		String pwd = loginInfo.getPwd();

		Account member = null;
//		member.setId(id);
//		member.setPwd(pwd);
		Account selectMember = null;

		try {
			selectMember = null;
			System.out.println("!!!!!!!!ssssssssssss!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (selectMember != null) {
			if (selectMember.getRole().equals("A")) {
				System.out.println("관리자가 로그인함");
				session.setAttribute("isAdmin", true);
			}

			System.out.println("로그인 성공");
			session.setAttribute("id", selectMember.getId());
			session.setAttribute("no", selectMember.getEmail());

			return true;
		}

		return false;
	}

	@Override
	public void logout() {
		if (session != null) {
			session.invalidate();
		}
	}
}