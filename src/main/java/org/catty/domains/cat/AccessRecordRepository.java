package org.catty.domains.cat;

import java.util.List;

public interface AccessRecordRepository {
    public List<AccessRecord> selectAccessRecordList(AccessRecord accessRecord) throws Exception;
    public AccessRecord selectAccessRecord(AccessRecord accessRecord) throws Exception;
    public List<AccessRecord> selectAccessRecordForTime(AccessRecord accessRecord) throws Exception;
    public void insertAccessRecord(AccessRecord accessRecord) throws Exception;
    public void updateAccessRecord(AccessRecord accessRecord) throws Exception;
    public void deleteAccessRecord(AccessRecord accessRecord) throws Exception;
}