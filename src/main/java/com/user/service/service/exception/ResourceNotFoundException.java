package com.user.service.service.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	//extra property if needed
	
	public ResourceNotFoundException() {
		super("Resource not found exception");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
