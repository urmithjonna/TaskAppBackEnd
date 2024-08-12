package com.example.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.JwtAuthResponse;
import com.example.project.dto.LoginDto;
import com.example.project.dto.Userdto;
import com.example.project.security.JWTTokenProvider;
import com.example.project.service.userService;

@RestController
@RequestMapping("/api/auth")
public class authController {
	@Autowired
	private userService uservice;
	@Autowired
	private AuthenticationManager authmanager;
	@Autowired
	private JWTTokenProvider jwt;
	
	//post - we store the user in DB
	@PostMapping("/register")
	public ResponseEntity<Userdto> createUser(@RequestBody Userdto userDto) {
		return new ResponseEntity<>(uservice.createUser(userDto),HttpStatus.CREATED);
		
	}
	@GetMapping("/{nam}/test")
	public ResponseEntity<String> validate(@PathVariable(name="nam" )String s){
		
		String n=s+"balu";
		return new ResponseEntity<>(n,HttpStatus.OK);
		
	}
	@PostMapping("/login")
	public  ResponseEntity<JwtAuthResponse>  loginUser(@RequestBody LoginDto logindto)
	{
		Authentication auth=authmanager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getEmail(), logindto.getPassword()));
		System.out.println(auth);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String token=jwt.generateToken(auth);
		return  ResponseEntity.ok(new JwtAuthResponse(token));
	}
}
