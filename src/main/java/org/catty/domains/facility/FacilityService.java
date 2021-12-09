package org.catty.domains.facility;

import java.util.List;
import java.util.Map;

import org.catty.domains.account.domain.Account;

public interface FacilityService{
	public boolean registerFacility(Facility facility,
			ManagementFacility managementFacility,
			FeedBarrel feedBarrel,
			WaterBarrel waterBarrel);
	public List<Facility> getFacilityList(String term);
	public Map<String, Object> getFacility(Facility facility);
	public boolean editFacility(Facility facility,
			FeedBarrel feedBarrel,
			WaterBarrel waterBarrel);
	public boolean deleteFacility(Facility facility);
	public void editFeedBarrelList(Facility facility, List<FeedBarrel> feedBarrel);
	public void editWaterBarrelList(Facility facility,  List<WaterBarrel> waterBarrel);
	public List<ManagementFacility> getManagementFacilityList(Facility facility);
	public List<ManagementFacility> getManagementFacilityList(Account member);
	public boolean registerManagementFacility(ManagementFacility managementFacility);
	public boolean deleteManagementFacilityList(ManagementFacility managementFacility);
	public void setTempFacilityUid(Facility facility);
	public List<FeedBarrel> getFeedBarrelList(int facilityNo);
	public List<WaterBarrel> getWaterBarrelList(int facilityNo);
}
