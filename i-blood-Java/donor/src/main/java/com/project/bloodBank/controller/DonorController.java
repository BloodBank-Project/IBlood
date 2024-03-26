package com.project.bloodBank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.project.bloodBank.bean.DonorBean;
import com.project.bloodBank.entity.DonorEntity;
import com.project.bloodBank.exceptions.DonorNotFoundException;
import com.project.bloodBank.serviceImpl.DonorServiceImpl;

@RestController
@RequestMapping("/donor")
public class DonorController {

	private static Logger log=LoggerFactory.getLogger(DonorController.class.getSimpleName());

	@Autowired
	private DonorServiceImpl donorServiceImpl;
	
	@PostMapping("/save")
	public ResponseEntity<DonorEntity> saveDonor(@RequestBody DonorEntity donorEntity){
		log.info(" Going to save Donar ");
		try {
			DonorEntity save = donorServiceImpl.saveDonor(donorEntity);
			ResponseEntity<DonorEntity> responseEntity = new ResponseEntity<>(save,HttpStatus.CREATED);
			log.info("Donor Saved Successfully");
			return responseEntity;
		}
		catch (DonorNotFoundException e) {
			log.error(" Saving Of Donor Unsuccessfull");
			return new ResponseEntity<DonorEntity>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<DonorEntity> getByDonorId(@PathVariable Long id){
		log.info(" Going to get Donar Id ");
		try {
			DonorEntity byDonorId = donorServiceImpl.getByDonorId(id);
			ResponseEntity<DonorEntity> responseEntity = new ResponseEntity<>(byDonorId, HttpStatus.OK);
			log.info("Getting DonorId Is Successed");
			return responseEntity;
		}
		catch (DonorNotFoundException e) {
			log.error(" Getting Of DonorId Unsuccessfull");
			return new ResponseEntity<DonorEntity>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<DonorEntity> updateDonor(@RequestBody DonorEntity donorEntity){
		log.info(" Going to Update Donar ");
		try {
			donorServiceImpl.updateDonor(donorEntity);
			ResponseEntity<DonorEntity> responseEntity = new ResponseEntity<>(HttpStatus.OK);
			log.info("Donor Updated Successfully");
			return responseEntity;
		}
		catch (DonorNotFoundException e) {
			log.error(" Updating Of Donor Unsuccessfull");
			return new ResponseEntity<DonorEntity>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<DonorEntity> deleteDonor(@PathVariable Long id){
		log.info(" Going to Delete Donar ");
		try {
			donorServiceImpl.deleteDonor(id);
			ResponseEntity<DonorEntity> responseEntity = new ResponseEntity<>(HttpStatus.OK);
			log.info("Donor Deleted Successfully");
			return responseEntity;
		}
		catch (DonorNotFoundException e) {
			log.error(" Deleting Of Donor Is Unsuccessfull");
			return new ResponseEntity<DonorEntity>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<DonorEntity>> donorsList(){
		log.info(" Going to Fetch Donar ");
		try {
			List<DonorEntity> allDonors = donorServiceImpl.getAll();
			ResponseEntity<List<DonorEntity>> responseEntity = new ResponseEntity<>(allDonors,HttpStatus.OK);
			log.info("Donor Fetched Successfully");
			return responseEntity;
		}
		catch (DonorNotFoundException e) {
			log.error(" fetching  Of Donor Unsuccessfull");
			return new ResponseEntity<List<DonorEntity>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<DonorBean> getUserDetails(@PathVariable Long id){
		
		log.info("fetch user details", id);
		try {
			DonorBean donorBean = donorServiceImpl.getByUserId(id);
		ResponseEntity<DonorBean> responseEntity = new ResponseEntity(donorBean, HttpStatus.OK);
		log.info("User Details Fetched Successfully");
		return responseEntity;
		}
		catch(DonorNotFoundException e) {
			log.error("User Details Fetched Unsuccessfull");
			return new ResponseEntity<DonorBean>(HttpStatus.NOT_FOUND);
		}
		

	}
	
	@GetMapping(path="/userId/{id}")
	public ResponseEntity<Long>  findByUserId(@PathVariable("id") Long id){
		 Long donorId = donorServiceImpl.findDonorIdByUserId(id);
		 return new ResponseEntity<Long>(donorId,HttpStatus.OK);
		
	}
	
}
