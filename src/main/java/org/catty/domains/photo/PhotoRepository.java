package org.catty.domains.photo;

import java.util.List;

/**
 * @author dbsehdghks45@naver.com
*/
public interface PhotoRepository {
    public int count(Photo photo) throws Exception;
    public List<Photo> selectPhotoList(Photo photo) throws Exception;
    public Photo selectPhoto(Photo photo) throws Exception;
    public int insertPhoto(Photo photo) throws Exception;
    public int updatePhoto(Photo photo) throws Exception;
    public int deletePhoto(Photo photo) throws Exception;
}