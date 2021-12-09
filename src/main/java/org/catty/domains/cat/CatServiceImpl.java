package org.catty.domains.cat;

import java.util.List;
import java.util.Map;

import org.catty.domains.facility.Facility;
import org.catty.domains.facility.FacilityRepository;
import org.catty.domains.observationlog.ObservationLog;
import org.catty.domains.observationlog.ObservationLogRepository;
import org.catty.domains.photo.Photo;
import org.catty.domains.photo.PhotoFileRepository;
import org.catty.domains.photo.PhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CatServiceImpl implements CatService {

	private CatRepository catRepository;

	private PhotoRepository photoRepository;

	private FacilityRepository facilityRepository;

	private AccessRecordRepository accessRecordRepository;
	private ObservationLogRepository observationLogRepository;

	private PhotoFileRepository photoFileRepository;

	@Override
	@Transactional
	public boolean registerCat(Cat cat, MultipartFile attach) {
		try {
			if (cat != null && attach != null) {
				Cat temp = new Cat();
				temp.setTagId(cat.getTagId());
				
				//1. 해당 고양이의 태그아이디가 중복 되는지 비교
				if (catRepository.selectCat(temp).getTagId().equals(cat.getTagId())) {
					// 임시 고양이 정보 초기화
					temp.setNo(-1);
					temp.setTagId("");
					temp.setName("");
					temp.setGender("M");
					temp.setTnrStatus("N");
					int initCat = catRepository.updateCat(temp);
					
					if (initCat > 0) {
						//2. 고양이 정보 등록
						if (cat.getBirthDate().trim().equals("")) {
							cat.setBirthDate(null);
						}
						if (cat.getFeature().trim().equals("")) {
							cat.setFeature(null);
						}
						if (cat.getSpices().trim().equals("")) {
							cat.setSpices(null);
						}
						int insertCatRow = catRepository.insertCat(cat);
						
						if (insertCatRow > 0) {
							cat = catRepository.selectCat(cat);
							System.out.println(cat);
							
							if (cat != null) {
								//3. 고양이 사진 파일 저장
								Photo photo = photoFileRepository.downloadFile(attach, 'C');
								if (photo != null) {
									photo.setTypeNo(cat.getNo());
									
									//4. 고양이 사진 정보 등록
									int insertPhotoRow = photoRepository.insertPhoto(photo);
									if (insertPhotoRow > 0) {
										
										return true;
									} else {
										photoFileRepository.deleteFile(photo);
										photoRepository.deletePhoto(photo);
										
										return false;
									}
								}
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<Cat> getCatList(String term, int sort) {
		List<Cat> result = null;	 
		
		try {
			//1. 고양이 정보 목록을 조회
			result =  catRepository.selectCatList(term, sort);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Cat getCat(Cat cat) {
		try {
			//1. 고양이 정보를 조회한다.
			return catRepository.selectCat(cat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@Transactional
	public boolean editCat(Cat cat, MultipartFile attach) {
		try {
			if (cat != null) {
				//1. 해당 고양이의 태그아이디가 중복 되는지 비교
				Cat temp = new Cat();
				temp.setTagId(cat.getTagId());
				
				if (catRepository.selectCat(temp) != null) {
					//2. 고양이 정보를 수정한다.
					int updateRow = catRepository.updateCat(cat);
					
					if (updateRow > 0) {
						//3. 사진파일을 수정 했는지 확인
						if (attach.getSize() > 0) {
							//3. 고양이 사진 수정
							Photo photo = new Photo();
							photo.setType("C");
							photo.setTypeNo(cat.getNo());
							photo = photoRepository.selectPhoto(photo);
							
							photo = photoFileRepository.updateFile(photo, attach);
							if (photo != null) {
								//4. 고양이 사진 정보 수정
								int insertPhotoRow = photoRepository.updatePhoto(photo);
								if (insertPhotoRow > 0) {
									
									return true;
								} else {
									photoFileRepository.deleteFile(photo);
									photoRepository.deletePhoto(photo);
									
									return false;
								}
							}
						}
						
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	@Transactional
	public boolean deleteCat(Cat cat) {
		try {
			//고양이 정보를 조회
			cat = catRepository.selectCat(cat);
			// 조회한 고양이가 존재하는 지 확인
			if (cat != null) {
				//존재한다면 삭제
				Photo photo = new Photo();
				photo.setType("C");
				photo.setTypeNo(cat.getNo());
				photo = photoRepository.selectPhoto(photo);
				
				if (photo != null) {
					// 고양이 사진 정보 삭제
					photoRepository.deletePhoto(photo);
					
					//고양이 사진 파일 삭제
					photoFileRepository.deleteFile(photo);
				}
				
				//고양이 접근 정보도 삭제
				AccessRecord accessRecord = new AccessRecord();
				accessRecord.setCatNo(cat.getNo());
				accessRecordRepository.deleteAccessRecord(accessRecord);
				
				//관찰일지 정보 삭제
				ObservationLog observationLog = new ObservationLog();
				observationLog.setCatNo(cat.getNo());
				observationLogRepository.deleteObservationLog(observationLog);
				
				int deleteRow = catRepository.deleteCat(cat);
				
				if (deleteRow > 0) {
					
					return true;					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean saveAccessRecord(Map<String, Object> object) {
		try {
			//1. 해당 시설물이 존재하는 지 확인한다.
			Facility facility = new Facility();
			facility.setUid(object.get("uid").toString());
			facility = facilityRepository.selectFacility(facility);
			
			//2. 해당 시설물이 존재하면 접근 정보를 저장한다.
			if (facility != null) {
				AccessRecord accessRecord = new AccessRecord();
				
				Map<String, Object> accessMap = (Map<String, Object>) object.get("accessRecord");
				System.out.println(accessMap);
				
				Cat cat = new Cat();
				cat.setTagId(accessMap.get("uid").toString());
				cat = catRepository.selectCat(cat);
				
				if (cat != null && (cat.getNo() != -1)) {
					accessRecord.setAccessTime(accessMap.get("accessTime").toString());
					accessRecord.setFacilityNo(facility.getNo());
					accessRecord.setCatNo(cat.getNo());
					
					accessRecordRepository.insertAccessRecord(accessRecord);
				}
				
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<AccessRecord> getAccessRecordList(Cat cat) {
		AccessRecord curAccessRecord = new AccessRecord();
		curAccessRecord.setCatNo(cat.getNo());
		List<AccessRecord> accessRecords = null;
		
		try {
			accessRecords = accessRecordRepository.selectAccessRecordList(curAccessRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accessRecords;
	}

	@Override
	public AccessRecord getAccessRecord(Cat cat) {
		AccessRecord accessRecord = new AccessRecord();
		accessRecord.setCatNo(cat.getNo());
		
		try {

			return accessRecordRepository.selectAccessRecord(accessRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public List<AccessRecord> getAccessRecordForTime(Cat cat) {
		AccessRecord curAccessRecord = new AccessRecord();
		curAccessRecord.setCatNo(cat.getNo());
		List<AccessRecord> accessRecords = null;
		
		try {
			accessRecords = accessRecordRepository.selectAccessRecordForTime(curAccessRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accessRecords;
	}
	
	@Override
	public boolean setTagId(Cat cat) {
		//1. 현재 입력 받은 태그 아이디가 존재하는지 비교하고 없으면 추가한다.
		try {
			if (cat != null && cat.getTagId() != null) {
				String tagId = cat.getTagId();
				
				cat = catRepository.selectCat(cat);
				if (cat == null) {
					cat = new Cat();
					cat.setNo(-1);
					cat.setTagId(tagId);
					cat.setName("");
					cat.setGender("M");
					cat.setTnrStatus("N");
					catRepository.updateCat(cat);
					
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}