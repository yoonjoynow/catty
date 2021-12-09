package org.catty.domains.observationlog;

import java.util.List;

import org.catty.domains.photo.Photo;
import org.catty.domains.photo.PhotoFileRepository;
import org.catty.domains.photo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ObservationLogServiceImpl implements ObservationLogService {
	@Autowired
	private ObservationLogRepository observationLogRepository;
	@Autowired
	private PhotoRepository photoRepository;
	@Autowired
	private PhotoFileRepository photoFileRepository;
	
	@Override
	@Transactional
	public boolean registerObservationLog(ObservationLog observationLog, MultipartFile attach) {
		try {
			//1. 관찰일지 등록
			int insertObsLogRow = observationLogRepository.insertObservationLog(observationLog);
			
			if (insertObsLogRow > 0) {
				if (attach.getSize() > 0) {
					observationLog = observationLogRepository.selectObservationLog(observationLog);
					
					if (observationLog != null) {
						//2. 관찰일지 사진 파일 저장
						Photo photo = photoFileRepository.downloadFile(attach, 'O');
						if (photo != null) {
							photo.setTypeNo(observationLog.getNo());
							
							//3. 관찰일지 사진 정보 등록
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
				
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<ObservationLog> getObservationLogList(ObservationLog observationLog) {
		try {
			//1. 관찰일지 리스트를 조회한다.
			return observationLogRepository.selectObservationLogList(observationLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ObservationLog getObservationLog(ObservationLog observationLog) {
		try {
			return observationLogRepository.selectObservationLog(observationLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public boolean editObservationLog(ObservationLog observationLog, MultipartFile attach) {
		try {
			int updateRow = observationLogRepository.updateObservationLog(observationLog);
			
			if (updateRow > 0) {
				if (attach.getSize() > 0) {
					//3. 관찰일지 사진 수정
					Photo photo = new Photo();
					photo.setType("O");
					photo.setTypeNo(observationLog.getNo());
					photo = photoRepository.selectPhoto(photo);
					
					int insertPhotoRow = 0;
					if (photo == null) {
						photo = photoFileRepository.downloadFile(attach, 'O');
						
						if (photo != null) {
							// 관찰일지 사진 정보 등록
							photo.setTypeNo(observationLog.getNo());
							insertPhotoRow = photoRepository.insertPhoto(photo);
						}
					} else {
						photo = photoFileRepository.updateFile(photo, attach);
						
						if (photo != null) {
							// 관찰일지 사진 정보 수정
							insertPhotoRow = photoRepository.updatePhoto(photo);
						}
					}
						
					if (insertPhotoRow > 0) {
						
						return true;
					} else {
						photoFileRepository.deleteFile(photo);
						photoRepository.deletePhoto(photo);
						
						return false;
					}
				}
				
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	@Transactional
	public boolean deleteObservationLog(ObservationLog observationLog) {
		try {
			observationLog = observationLogRepository.selectObservationLog(observationLog);
			
			if (observationLog != null) {
				//존재한다면 삭제
				Photo photo = new Photo();
				photo.setType("O");
				photo.setTypeNo(observationLog.getNo());
				photo = photoRepository.selectPhoto(photo);
				
				if (photo != null) {
					// 관찰일지 사진 정보 삭제
					photoRepository.deletePhoto(photo);
					//관찰일지 사진 파일 삭제
					photoFileRepository.deleteFile(photo);
				}

				int deleteRow = observationLogRepository.deleteObservationLog(observationLog);
				if (deleteRow > 0) {
					
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
