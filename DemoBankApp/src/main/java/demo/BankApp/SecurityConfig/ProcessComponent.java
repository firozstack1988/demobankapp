package com.firoztechi.payrollapp.scheduler.component;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class ProcessComponent {

	@Autowired
	ScheduleService schedulerService;
	
	@Autowired
	List<SchedulingService> schedulingServices;
	
	@Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
		Calendar cal = Calendar.getInstance();
		
        List<ScheduleEntity> schedules = schedulerService.getList();
        schedules.stream()
        	.forEach(s-> {
        		
        		if(s.isRunning())
        		{
        			if(scheduledNow(cal, s)) {
    	        		Optional<SchedulingService> scheduleJobOpt = getScheduler(s.getName());
    	        		if(scheduleJobOpt.isPresent()) {
   
    	        			//new Thread(() -> {scheduleJobOpt.get().execute();}).start();
    	 
    	        		}else {
    	        	
    	        		}
    	        	}else {
    	        		
    	        	}
        		}
        		else
        		{
        			//log.debug("Schedule Job "+s.getName() + " is Not Running (isRunning:" + s.getIsRunning() + ") :so no job is executed");
        		}
        		//log.info("Schedule Job "+s.getName() + " execution completed ");
        	});
        
        //log.info("Scheduler ended at: " + System.currentTimeMillis()/1000);
    }
	
	@Scheduled(fixedDelay = 5430000) //it will run after 1 hour 30mins 30Sec
    public void reloadSchedularList() {
		Calendar cal = Calendar.getInstance();
		//log.info("Scheduler for Reload SchedularList started at: " + cal);
        schedulerService.reloadSchedularList();
        //log.info("Scheduler reload SchedularList  at: " + System.currentTimeMillis()/1000);
    }
	
	private boolean scheduledNow(Calendar cal, ScheduleEntity s) {
		if(cal.get(Calendar.YEAR) == s.getYear() || s.getYear() == -1) {
			if(cal.get(Calendar.MONTH)+1 == s.getMonth() || s.getMonth() == -1) {
				if(cal.get(Calendar.DAY_OF_MONTH) == s.getDay() || s.getDay() == -1) {
					if(cal.get(Calendar.HOUR_OF_DAY) == s.getHours() || s.getHours() == -1) {
						if(cal.get(Calendar.MINUTE) == s.getMinute() || s.getMinute() == -1) {
							if(cal.get(Calendar.SECOND) == s.getSecond() || s.getSecond() == -1) {
								return true;
							}else {
								return false;
							}
						}else {
							return false;
						}
					}else {
						return false;
					}
				}else {
					return false;
				}
				
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	private Optional<SchedulingService> getScheduler(String name) {
		return this.schedulingServices.stream()
				.filter(s->s.getName().equalsIgnoreCase(name))
				.findFirst();
	}

}
