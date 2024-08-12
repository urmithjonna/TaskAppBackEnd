package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	 public List<Task> findAllByUsersId(Long userid);

	
	

}
