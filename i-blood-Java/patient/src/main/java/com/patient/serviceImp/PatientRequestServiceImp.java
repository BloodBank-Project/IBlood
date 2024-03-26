package com.patient.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.bean.PatientRequestBean;
import com.patient.entity.PatientRequest;
import com.patient.exception.PatientRequestNotFoundException;
import com.patient.repository.PatientRequestRepository;
import com.patient.service.PatientRequestService;
@Service
public class PatientRequestServiceImp implements PatientRequestService{
	@Autowired
	private PatientRequestRepository requestRepository;
	
	@Override
	public PatientRequest save(PatientRequest patientRequest) {
		return requestRepository.save(patientRequest);
	}

	@Override
	public void deletePatientRequest(Long id) {
		Optional<PatientRequest> optional = requestRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new PatientRequestNotFoundException("PatientRequest id is not Found"));
			} catch (PatientRequestNotFoundException e) {
				throw e;
			}
		} else {
			requestRepository.deleteById(id);
		}

	}
	
	@Override
	public List<PatientRequestBean> getAllPatientRequest() {
		List<PatientRequest> entities = requestRepository.findAll();
		List<PatientRequestBean> beans = new ArrayList<>();
	
		for(PatientRequest patientRequest:entities) {
			Long request_id = patientRequest.getPatientRequestId();
			System.out.println(request_id);
			String bloodGroupRepo = requestRepository.getBloodGroupType(request_id);
			System.out.println(bloodGroupRepo);
			PatientRequestBean bloodGroupBean = requestRepository.getBloodGroup(request_id);
			beans.add(bloodGroupBean);
			System.out.println(bloodGroupBean);
		}
		return beans;
	
	}

	@Override
	public Optional<PatientRequest> updatePatientRequest(PatientRequest patientRequest) {
		Optional<PatientRequest> optional = requestRepository.findById(patientRequest.getPatientRequestId());
		if (optional.isPresent()) {
			Long patient_id = optional.get().getPatientId();
			patientRequest.setPatientId(patient_id);
			requestRepository.save(patientRequest);
			return optional;
		}
		return Optional.of(
				optional.orElseThrow(() -> new PatientRequestNotFoundException("No request is found for updation")));
	}

	@Override
	public PatientRequestBean getPatientRequest(Long id) {
		Optional<PatientRequest> optional = requestRepository.findById(id);
		PatientRequestBean requestBean = new PatientRequestBean();
		
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new PatientRequestNotFoundException("Request is not found"));
			} catch (PatientRequestNotFoundException e) {
				throw e;
			}
		}
		else {
			Long request = optional.get().getPatientRequestId();
			String bloodGroupRepo = requestRepository.getBloodGroupType(request);
			requestBean=requestRepository.getBloodGroup(request);
			System.out.println(requestBean);
		}
		return requestBean;
	}
}
