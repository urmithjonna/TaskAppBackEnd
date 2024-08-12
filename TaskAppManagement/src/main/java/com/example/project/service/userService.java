package com.example.project.service;

import org.springframework.stereotype.Service;

import com.example.project.dto.Userdto;
@Service
public interface userService {
	public Userdto createUser(Userdto userDto);
	

}
