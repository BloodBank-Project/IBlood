package com.patient.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.patient.bean.PatientBean;
import com.patient.bean.PatientRequestBean;
import com.patient.bean.UserBean;
import com.patient.entity.Patient;
import com.patient.entity.PatientRequest;
import com.patient.exception.PatientNotFoundException;
import com.patient.repository.PatientRepository;
import com.patient.service.PatientService;

@Service
public class PatientServiceImp implements PatientService {
	@Autowired
	private PatientRepository repository;

	@Autowired
	private RestTemplate restTemplate;

//	private Optional<Patient> optional;

	@Override
	public Patient save(Patient patient) {
		return repository.save(patient);

	}

	@Override
	public Patient get(Long id) {
		Optional<Patient> optional = repository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new PatientNotFoundException("Patient not found"));
			} catch (PatientNotFoundException e) {
				throw e;
			}
		}
		return optional.get();
	}

	@Override
	public void delete(Long id) {
		Optional<Patient> optional = repository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new PatientNotFoundException("Patient id is not Found"));
			} catch (PatientNotFoundException e) {
				throw e;
			}
		} else {
			repository.deleteById(id);
		}
	}

	@Override
	public List<Patient> getAll() {
		List<Patient> list = repository.findAll();
		return list;
	}

	@Override
	public Optional<Patient> update(Patient patient) {
		Optional<Patient> optional = repository.findById(patient.getPatientId());
		if (optional.isPresent()) {
			repository.save(patient);
			return optional;
		} else {
			optional.orElseThrow(() -> new PatientNotFoundException("No Patient for Updation"));
		}
		return null;
	}

//================================= PatientBean ============================================

	@Override
	public PatientBean getById(Long id) {
		Optional<Patient> optional = repository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new PatientNotFoundException("Patient id is not Found"));
			} catch (PatientNotFoundException e) {
				throw e;
			}
		}

		Patient patient = optional.get();
		
		List<PatientRequest> patientRequests = patient.getPatientRequest();
		
		List<PatientRequestBean> patientRequestBeans = new ArrayList<>();
		for (PatientRequest patientRequest : patientRequests) {
			PatientRequestBean patientRequestBean = PatientRequestBean.builder()
					.patientRequestId(patientRequest.getPatientRequestId())
					.patientMedicalCondition(patientRequest.getPatientMedicalCondition())
					.patientRequestOnDate(patientRequest.getPatientRequestOnDate())
					.patientRequestUnits(patientRequest.getPatientRequestUnits())
					.status(patientRequest.getStatus()).build();
			patientRequestBeans.add(patientRequestBean);
		}

		String Userurl = "http://localhost:8083/user/" + patient.getUserId();

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<UserBean> reponseEntity = restTemplate.exchange(Userurl, HttpMethod.GET, httpEntity,
				UserBean.class);

		UserBean userBean = reponseEntity.getBody();
//		BloodBankBean bankBean = userBean.getBlood_bank_id();
		userBean.getBloodGroupId();

//		BloodBankBean bloodBankBean = BloodBankBean.builder().blood_bank_id(bankBean.getBlood_bank_id())
//				.blood_bank_name(bankBean.getBlood_bank_name()).blood_bank_location(bankBean.getBlood_bank_location())
//				.available_blood_group(bankBean.getAvailable_blood_group()).blood_quantity(bankBean.getBlood_quantity())
//				.build();

//		userBean.setBlood_bank_id(bankBean);
//		userBean.setBlood_group_id(userBean.getBlood_group_id());

		PatientBean patientbean = PatientBean.builder().patientId(patient.getPatientId()).userDetails(userBean)
				.patientRequestDetails(patientRequestBeans).build();

		return patientbean;
	}

	@Override
	public Long findPatientIdByUserId(Long userId) {
		Long id = repository.findIdByUserId(userId);
		return id;
	}

}
