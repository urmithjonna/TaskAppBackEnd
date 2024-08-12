package com.example.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class apiException extends RuntimeException{
	private String message;
	public apiException(String message)
	{
		super(message);
		this.message=message;
	}

}
