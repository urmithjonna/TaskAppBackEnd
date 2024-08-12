package com.example.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Taskdto {
	
	private Long id;
	private String taskName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

}
