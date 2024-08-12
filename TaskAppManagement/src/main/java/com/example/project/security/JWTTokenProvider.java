package com.example.project.security;


import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.project.exception.apiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenProvider {
	public String generateToken(Authentication auth)
	{
		String email=auth.getName();
		java.util.Date currentDate=new Date();
		Date ExpireDate=new Date(currentDate.getTime()+3600000);//format of milli seconds i.e, expiration perid is i hour
		String token=Jwts.builder().setSubject(email).setIssuedAt(currentDate).setExpiration(ExpireDate).signWith(SignatureAlgorithm.HS512 
				,"JWTSecretKey").compact();
		
		return token;
		
		
		
	}
	
	public String getEmail(String token)
	{
	Claims claims=Jwts.parser().setSigningKey("JWTSecretKey")
		
		.parseClaimsJws(token).getBody();
	
		return claims.getSubject();
	}
	
	public boolean validateToken(String token)
	{
		try
		{
			Jwts.parser().setSigningKey("JWTSecretKey").parseClaimsJws(token);
			return true;
			
		}
		catch(Exception e)
		{
			throw new apiException("Token issue:"+e.getMessage());
			
		}
	}

}
