package com.firoztechi.payrollapp.scheduler.component;

import java.util.List;

public class SchedulingService {

	private boolean isRunning;
	private String name;
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SchedulingService> reloadSchedularList() {
		
		return null;
	}
}
