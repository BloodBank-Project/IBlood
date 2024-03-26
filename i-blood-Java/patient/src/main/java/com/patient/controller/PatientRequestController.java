package com.patient.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.bean.PatientRequestBean;
import com.patient.entity.Patient;
import com.patient.entity.PatientRequest;
import com.patient.exception.PatientNotFoundException;
import com.patient.exception.PatientRequestNotFoundException;
import com.patient.service.PatientRequestService;
import com.patient.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/patientRequest")
public class PatientRequestController {
	@Autowired
	private PatientRequestService patientRequestService;

	@PostMapping("/save")
	public ResponseEntity<PatientRequest> savePatientRequest(@RequestBody PatientRequest patientRequest) {
		log.info("Going to Save Details");
		try {
		patientRequestService.save(patientRequest);
		ResponseEntity<PatientRequest> responseEntity = new ResponseEntity(patientRequest, HttpStatus.CREATED);
		log.info("saved Successfully");
		return responseEntity;
		}
		catch (PatientRequestNotFoundException e) {
			log.error("Error Occured While Saving");
			return new ResponseEntity<PatientRequest>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PatientRequest> deletePatientRequest(@PathVariable Long id) {
		log.info("Going To Delete Patient");
		try {
			patientRequestService.deletePatientRequest(id);
			ResponseEntity<PatientRequest> responseEntity = new ResponseEntity(HttpStatus.OK);
			log.info("Deleted Successfully");
			return responseEntity;
		}catch(PatientRequestNotFoundException e) {
			log.error("Error Occured While Deleting");
			return new ResponseEntity<PatientRequest>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/allPatients")
	public ResponseEntity<List<PatientRequestBean>> getAllPatientRequest() {
		log.info("Going to Fetch Data ");
		try {
		List<PatientRequestBean> list = patientRequestService.getAllPatientRequest();
		ResponseEntity<List<PatientRequestBean>> responseEntity = new ResponseEntity(list, HttpStatus.OK);
		log.info("Fetched Successfully");
		return responseEntity;
		}
		catch (PatientRequestNotFoundException e) {
			log.error("Error Ocuured while Fetching");
			return new ResponseEntity<List<PatientRequestBean>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PatientRequestBean> getPatientRequest(@PathVariable Long id){
		log.info("Going To Get Patient Details");
		try {
			PatientRequestBean patientRequest = patientRequestService.getPatientRequest(id);
		ResponseEntity<PatientRequestBean> patient=new ResponseEntity<>(patientRequest,HttpStatus.OK);
		log.info("Patient Details Got Successfully");
		return patient;
		}
		catch(PatientRequestNotFoundException e) {
			log.error("Error occured while Fetching patient");
			return new ResponseEntity<PatientRequestBean>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Optional<PatientRequest>> updatePatientRequest(@RequestBody PatientRequest patientRequest) {
		log.info("Going to Update Patient Details");
		try {
		Optional<PatientRequest> updatePatientRequest = patientRequestService.updatePatientRequest(patientRequest);
		ResponseEntity<Optional<PatientRequest>> responseEntity = new ResponseEntity<>(updatePatientRequest,
				HttpStatus.OK);
		log.info("Updated Successfully");
		return responseEntity;}
		catch(PatientRequestNotFoundException e) {
			log.error("Error Occured While Updating");
			return new ResponseEntity<Optional<PatientRequest>>(HttpStatus.NOT_FOUND);
		}
	}
}
