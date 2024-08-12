package com.example.project.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JWTAuthFilter extends OncePerRequestFilter{
	@Autowired
	private JWTTokenProvider jwtprovider;
	@Autowired
	private CustomUserDetailsService custService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//get token from header 
		String token=getToken(request);
		//check the token whether valid or not
		
		if(StringUtils.hasText(token) && jwtprovider.validateToken(token))
		{
			String email=jwtprovider.getEmail(token);
			UserDetails userdetails=custService.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken authenticate =new UsernamePasswordAuthenticationToken( userdetails,null,userdetails.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authenticate);
		}
		
		filterChain.doFilter(request, response);
		//if valid , load the user and set Authentication 
		
		
		
	}
	
	private String getToken(HttpServletRequest request)
	{
		String token=request.getHeader("Authorization");
		if(StringUtils.hasText(token) && token.startsWith("Bearer " ))
		{
			return token.substring(7,token.length());
		}
		return null;
	}
}
