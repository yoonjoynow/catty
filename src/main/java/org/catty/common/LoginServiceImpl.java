package org.catty.common;

import javax.servlet.http.HttpSession;

import org.catty.account.domain.Account;
import org.catty.account.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private MemberMapper memberMapper;

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
			selectMember = memberMapper.selectMember(member);
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