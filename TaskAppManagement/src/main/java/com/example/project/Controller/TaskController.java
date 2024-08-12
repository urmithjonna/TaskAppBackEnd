package com.example.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.Taskdto;
import com.example.project.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	@Autowired
	private TaskService tService;
	//to save task
	@PostMapping("/{user_id}/task")
	public ResponseEntity<Taskdto> saveTask(@PathVariable(name="user_id" ) long user_id,@RequestBody Taskdto taskdto)
	{
		
		
		return new ResponseEntity<>(tService.saveTask(user_id,taskdto),HttpStatus.CREATED);
		
	}
	
	//get all tasks
	@GetMapping("/{user_id}/alltasks")
	public ResponseEntity<List<Taskdto>> getAllTasks(
			@PathVariable(name="user_id") long user_id)
	{
		return new ResponseEntity<>(tService.getAlltasks(user_id),HttpStatus.OK);
	}
			
	//get individual task
	
	@GetMapping("/{user_id}/{taskId}")
	public ResponseEntity<Taskdto> getTask(@PathVariable(name="user_id") long user_id,@PathVariable(name="taskId") long taskId)
	{
		return new ResponseEntity<>(tService.getTask(user_id,taskId),HttpStatus.OK);
		
	}
	//delete individual task

	@DeleteMapping("/{user_id}/delete/{taskId}")
	public ResponseEntity<String> deleteTask(
			@PathVariable(name="user_id") long user_id,@PathVariable(name="taskId") long taskId)
	{
		tService.deleteTask(user_id, taskId);
		return  new ResponseEntity<>("task deleted successfully",HttpStatus.OK);
	}
}


