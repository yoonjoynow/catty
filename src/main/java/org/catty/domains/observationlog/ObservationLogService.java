package org.catty.domains.observationlog;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ObservationLogService {
	public boolean registerObservationLog(ObservationLog observationLog, MultipartFile attach);
	public List<ObservationLog> getObservationLogList(ObservationLog observationLog);
	public ObservationLog getObservationLog(ObservationLog observationLog);
	public boolean editObservationLog(ObservationLog observationLog, MultipartFile attach);
	public boolean deleteObservationLog(ObservationLog observationLog);
}
