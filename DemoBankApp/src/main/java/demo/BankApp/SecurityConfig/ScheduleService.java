package com.firoztechi.payrollapp.scheduler.component;

import java.util.List;

public interface ScheduleService {
 
	List<ScheduleEntity> getList();
	List<SchedulingService> reloadSchedularList();
	
}
