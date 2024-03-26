package com.patient.service;

import java.util.List;
import java.util.Optional;

import com.patient.bean.PatientBean;
import com.patient.entity.Patient;

public interface PatientService {
	Patient save(Patient patient);

	Patient get(Long id);

	Optional<Patient> update(Patient patient);

	void delete(Long id);

	List<Patient> getAll();
	
	PatientBean getById(Long id);
	
	Long findPatientIdByUserId(Long userId);

} 