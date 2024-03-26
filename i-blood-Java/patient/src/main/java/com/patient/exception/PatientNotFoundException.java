package com.patient.exception;

public class PatientNotFoundException extends RuntimeException {


	public PatientNotFoundException(String message) {
		super(message);
	}
	public PatientNotFoundException(String message,Exception exception) {
		super(message,exception);
	}

}
