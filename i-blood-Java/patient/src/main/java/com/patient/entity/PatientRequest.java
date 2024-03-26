package com.patient.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientRequestId;
	private Long patientRequestUnits;
	private Date patientRequestOnDate;
	private String patientMedicalCondition;
	private Long patientId;
	private String status;
	
	
}