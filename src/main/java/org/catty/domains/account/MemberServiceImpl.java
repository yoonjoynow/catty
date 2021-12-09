package org.catty.domains.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.catty.domains.account.dao.AccountRepository;
import org.catty.domains.account.domain.Account;
import org.catty.domains.account.dto.SignUpRequest;
import org.catty.domains.facility.Facility;
import org.catty.domains.facility.FacilityRepository;
import org.catty.domains.facility.ManagementFacility;
import org.catty.domains.facility.ManagementFacilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final AccountRepository accountRepository;
	@Autowired
	private ManagementFacilityMapper managementFacilityMapper;
	@Autowired
	private FacilityRepository facilityRepository;

	@Override
	public boolean joinMember(SignUpRequest signUpRequest) {


		return false;
	}
	
	@Override
	public boolean checkIdDuplication(String id) {

		
		return false;
	}

	@Override
	public List<Account> getMemberList(String term) {
		List<Account> memberList = null;

		try {
//			memberList = accountRepository.findAll(term);
			memberList = accountRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return memberList;
	}
	
	@Override
	public Account getMember(Account member) {
		try {
			member = accountRepository.findById(member.getId()).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return member;
	}

	@Override
	public boolean editMember(SignUpRequest signUpRequest) {

		return false;
	}

	@Override
	public boolean withdrawal(Account member) {
		return false;
	}
	
	@Override
	public Map<String, Object> getFacilityInChargeList(Account member) {
		Map<String, Object> memberData = new HashMap<String, Object>();;
		List<ManagementFacility> facilities = null;
		List<String> facilityNames = null;
		
		ManagementFacility managementFacility = new ManagementFacility();
		managementFacility.setMemberNo(member.getId());

		try {
			member = accountRepository.findById(member.getId()).get();
			
			facilities = managementFacilityMapper.selectManagementFacilityList(managementFacility);
			facilityNames = getManagementFacilityForName(facilities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		memberData.put("member", member);
		memberData.put("facilityNames", facilityNames);
		
		System.out.println("담당 시설물 목록 : " + facilityNames);

		return memberData;
	}
	
	public List<String> getManagementFacilityForName(List<ManagementFacility> facilities) {
		List<String> facilityNames = new ArrayList<String>();
		String facilityName = "";
		
		try {
			for (ManagementFacility value : facilities) {
				Facility facility = new Facility();
				facility.setNo(value.getFacilityNo());
				
				facility = facilityRepository.selectFacility(facility);
				System.out.println("########" + facility.getName());
				facilityName = facility.getName();
				
				facilityNames.add(facilityName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return facilityNames;
	}
}