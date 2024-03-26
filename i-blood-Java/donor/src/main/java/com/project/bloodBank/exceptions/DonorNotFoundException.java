package com.project.bloodBank.exceptions;

import org.springframework.http.HttpStatus;

public class DonorNotFoundException extends RuntimeException {


	public DonorNotFoundException(String message) {
		super(message);
	}
	
	public DonorNotFoundException(String message,Exception exception) {
		super(message , exception);
	}

	

}
