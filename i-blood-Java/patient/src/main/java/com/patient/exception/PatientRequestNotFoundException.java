package com.patient.exception;

public class PatientRequestNotFoundException extends RuntimeException{
	
	public PatientRequestNotFoundException(String message) {
		super(message);
	}
	public PatientRequestNotFoundException(String message,Exception exception) {
		super(message,exception);
	}

}
