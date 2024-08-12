package com.example.project.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.project.entity.Users;
import com.example.project.exception.UserNotFound;
import com.example.project.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository urepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users u=urepository.findByEmail(email).orElseThrow(
				()->new UserNotFound(String.format("invalid email %s not found", email)));
		Set<String> roles=new HashSet<String>();
		roles.add("ROLE_ADMIN");
		return new User(u.getEmail(),u.getPassword(),userAuthorities(roles));
	}
	
	
	
	private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles){
		
		return roles.stream().map(
				role-> new SimpleGrantedAuthority(role)
				).collect(Collectors.toList());
		
	}

}
