package com.project.bloodBank.exceptions;

public class DonationDetailsNotFoundException extends RuntimeException {

	public DonationDetailsNotFoundException(String message) {
		super(message);
	}
	
	public DonationDetailsNotFoundException(String message,Exception exception) {
		super(message , exception);
	}
	
	
}
