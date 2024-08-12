package com.example.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.dto.Taskdto;

@Service
public interface TaskService {
	
	public Taskdto saveTask(long user_id,Taskdto taskDto);
	
	public List<Taskdto> getAlltasks(long user_id);
	
	public Taskdto getTask(long user_id,long taskId);
	
	public void deleteTask(long user_id,long taskId);


	
	

}
