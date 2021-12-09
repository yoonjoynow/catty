package org.catty.domains.facility;

import java.util.List;

public interface FacilityRepository {
    public Facility selectFacility(Facility facility) throws Exception;
    public int insertFacility(Facility facility) throws Exception;
    public int updateFacility(Facility facility) throws Exception;
    public int deleteFacility(Facility facility) throws Exception;
    public List<Facility> selectFacilityList(String term) throws Exception;
    public int insertTempFacility(Facility facility) throws Exception;
}