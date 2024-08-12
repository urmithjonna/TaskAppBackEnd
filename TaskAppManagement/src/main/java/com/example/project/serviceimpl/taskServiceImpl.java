package com.example.project.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dto.Taskdto;
import com.example.project.entity.Task;
import com.example.project.entity.Users;
import com.example.project.exception.TaskNotFound;
import com.example.project.exception.UserNotFound;
import com.example.project.exception.apiException;
import com.example.project.repository.TaskRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.TaskService;

@Service
public class taskServiceImpl implements TaskService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository urepository;
	
	@Autowired
	private TaskRepository trepository;
	
	public Taskdto saveTask(long user_id, Taskdto taskDto) {
		Users u=urepository.findById(user_id).orElseThrow(
				() -> new UserNotFound(String.format("user_id %d not found",user_id)));
				
				
		Task t=modelMapper.map(taskDto,Task.class);
		t.setUsers(u);
		Task savedTask=trepository.save(t);
		
		return modelMapper.map(savedTask, Taskdto.class);
	}

	
	public List<Taskdto> getAlltasks(long user_id) {
		// TODO Auto-generated method stub
		urepository.findById(user_id).orElseThrow(
				()-> new UserNotFound(String.format("user_id %d not found ", user_id)));
		List<Task> t=trepository.findAllByUsersId(user_id);
		return t.stream().map(task->modelMapper.map(task, Taskdto.class)).collect(Collectors.toList());
	}


	
	public Taskdto getTask(long user_id, long taskId) {
		// TODO Auto-generated method stub
		Users u=urepository.findById(user_id).orElseThrow(
				()-> new UserNotFound(String.format("user with %d not found",user_id))
				);
		Task t=trepository.findById(taskId).orElseThrow(
				()-> new TaskNotFound(String.format("task with id %d not found",taskId)));
		if( u.getId()!=t.getUsers().getId())
		{
			throw new apiException(String.format("taskid %d not belongs to user_id %d", taskId,user_id));
		}
		return modelMapper.map(t,Taskdto.class);
	}



	public void deleteTask(long user_id, long taskId) {
		// TODO Auto-generated method stub
		Users u=urepository.findById(user_id).orElseThrow(
				()-> new UserNotFound(String.format("user with %d not found",user_id))
				);
		Task t=trepository.findById(taskId).orElseThrow(
				()-> new TaskNotFound(String.format("task with id %d not found",taskId)));
		if( u.getId()!=t.getUsers().getId())
		{
			throw new apiException(String.format("taskid %d not belongs to user_id %d", taskId,user_id));
		}
		trepository.deleteById(taskId);
		
	}

}
