package com.example.project.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.dto.Userdto;
import com.example.project.entity.Users;
import com.example.project.repository.UserRepository;
import com.example.project.service.userService;
@Service
public class userServiceImpl implements userService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	@Override
	public Userdto createUser(Userdto userDto) {
		//userDto is not an entity of Users
		//converting userDto to users(object)
		
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Users user=userDtoToEntity(userDto);
	     Users savedUser=userRepository.save(user);
		return entityToUserdto(savedUser);
		// TODO Auto-generated method stub
		
	}
	
	private Users userDtoToEntity(Userdto userDto)
	{
		Users u =new Users();
		u.setName(userDto.getName());
		u.setEmail(userDto.getEmail());
		u.setPassword(userDto.getPassword());
		return u;
		
	}
	
	private Userdto entityToUserdto(Users savedUser)
	{
		Userdto udto=new Userdto();
		udto.setId(savedUser.getId());
		udto.setName(savedUser.getName());
		udto.setEmail(savedUser.getEmail());
		udto.setPassword(savedUser.getPassword());
		return udto;
		
	}

}
