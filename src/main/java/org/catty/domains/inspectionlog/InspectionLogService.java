package org.catty.domains.inspectionlog;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface InspectionLogService {
	public boolean registerInspectionLog(InspectionLog inspectionLog, MultipartFile attach);
	public List<InspectionLog> getInspectionLogList(String term);
	public InspectionLog getInspectionLog(InspectionLog inspectionLog);
	public boolean editInspectionLog(InspectionLog inspectionLog, MultipartFile attach);
	public boolean deleteInspectionLog(InspectionLog inspectionLog);
}
