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

import com.patient.bean.PatientBean;
import com.patient.entity.Patient;
import com.patient.exception.PatientNotFoundException;
import com.patient.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping("/save")
	public ResponseEntity<Patient> save(@RequestBody Patient patinet) {
		log.info("Save PatientDetails", patinet);
		try {
		Patient patient = patientService.save(patinet);
		ResponseEntity<Patient> responseEntity = new ResponseEntity(patient, HttpStatus.CREATED);
		log.info("saved Succeefully");
		return responseEntity;
		}
		catch (PatientNotFoundException e) {
			log.error("saved Unsuuccessfully");
			return new ResponseEntity<Patient>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Patient> getByPatientId(@PathVariable Long id) {
		log.info(" Going to get fetch Patient Id ");
//		try {
			Patient byPatientId = patientService.get(id);
			ResponseEntity<Patient> responseEntity = new ResponseEntity<>(byPatientId, HttpStatus.OK);
			log.info("Getting Patient Is Successed");
			return responseEntity;
//		} catch (PatientIdNotFoundException e) {
//			log.error(" Getting Of PatientId Unsuccessfull");
//			return new ResponseEntity<PatientEntity>(HttpStatus.NOT_FOUND);
//		}
	}

//	@GetMapping("/{getById}")
//	public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
//		Patient patient = patientService.get(id);
//		ResponseEntity<Patient> responseEntity = new ResponseEntity(patient, HttpStatus.OK);
//		return responseEntity;
//	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
		log.info("Delete patient", id);
		try {
		patientService.delete(id);
		ResponseEntity<Patient> responseEntity = new ResponseEntity( HttpStatus.OK);
		log.info("deleted Succeefully");
		return responseEntity;
		}catch(PatientNotFoundException e) {
			log.error("not deleted");
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Patient>> getAllPatient() {
		log.info("Fetch allpatientdetilas", log);
		try {
		List<Patient> list = patientService.getAll();
		ResponseEntity<List<Patient>> responseEntity = new ResponseEntity(list, HttpStatus.OK);
		log.info("fetched Successfully");
		return responseEntity;
		}
		catch (PatientNotFoundException e) {
			log.error("not fetched");
			return new ResponseEntity<List<Patient>>(HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Patient>  updatePatient(@RequestBody Patient patient) {
		log.info("update Patient", patient);
		try {
		Optional<Patient> update = patientService.update(patient);	
		ResponseEntity<Patient> responseEntity = new ResponseEntity(update, HttpStatus.OK);
		log.info("Updated Successfully");
		return responseEntity;
		}catch(PatientNotFoundException e) {
			log.error("Not Updated");
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(path="/user/{id}")
	public ResponseEntity<PatientBean> getUserDetails(@PathVariable Long id){
		
		log.info("fetch patient()", id);
		try {
		PatientBean patientBean = patientService.getById(id);
		ResponseEntity<PatientBean> responseEntity = new ResponseEntity(patientBean, HttpStatus.OK);
		log.info("Successfully fetched User Details");
		return responseEntity;
		}
		catch(PatientNotFoundException e) {
			log.error("User deetails are not fetched");
			return new ResponseEntity<PatientBean>(HttpStatus.NOT_FOUND);
		}

	}
	
	@GetMapping(path="/userId/{id}")
	public ResponseEntity<Long>  findByUserId(@PathVariable("id") Long id){
		 Long patientId = patientService.findPatientIdByUserId(id);
		 return new ResponseEntity<Long>(patientId,HttpStatus.OK);
		
	}

}
