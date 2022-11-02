package com.usermgm.advice;

import java.util.HashMap;
import java.util.Map;

import com.usermgm.exception.globalException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class userExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	
	//Handling Exceptions of Data validations
	public Map<String,String> handleException(MethodArgumentNotValidException ex){
		
		Map<String,String> errorMap = new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		
		return errorMap;
	}
	
	@ExceptionHandler(globalException.class)
	
	public Map<String,String> handleGlobalException(globalException gex){
		
		Map<String,String> errorMap = new HashMap<>();
		
		errorMap.put("Exception",gex.getMessage());
		
		return errorMap;
	
	}

}
