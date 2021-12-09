package org.catty.domains.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {
	@Autowired
	private PhotoRepository photoRepository;
	@Autowired
	private PhotoFileRepository photoFileRepository;
	
	public byte[] getPhoto(Photo photo) {
		try {
			photo = photoRepository.selectPhoto(photo);
			if (photo != null) {
				byte[] file =  photoFileRepository.getFile(photo);
				
				if (file != null) {
					
					return file;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
