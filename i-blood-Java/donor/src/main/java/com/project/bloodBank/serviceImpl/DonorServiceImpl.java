package com.project.bloodBank.serviceImpl;

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

import com.project.bloodBank.bean.BloodBankBean;
import com.project.bloodBank.bean.BloodBean;
import com.project.bloodBank.bean.DonorBean;
import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.bean.UserBean;
import com.project.bloodBank.entity.DonorDetailsEntity;
import com.project.bloodBank.entity.DonorEntity;
import com.project.bloodBank.exceptions.DonorNotFoundException;
import com.project.bloodBank.repository.DonorRepository;
import com.project.bloodBank.service.DonorService;


@Service
public class DonorServiceImpl implements DonorService {

	@Autowired
	private DonorRepository donorRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public DonorEntity saveDonor(DonorEntity donorEntity) {
		try {
			DonorEntity donoEntity = donorRepository.save(donorEntity);
		return donoEntity;
		} catch (DonorNotFoundException e) {
			throw e;
		}
	}

	@Override
	public DonorEntity getByDonorId(Long id) {
		Optional<DonorEntity> optional = donorRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new DonorNotFoundException("donor is not found to delete with this " + id));
			} catch (DonorNotFoundException e) {
				throw e;
			}
		}
		return optional.get();
	}

	@Override
	public void updateDonor(DonorEntity donorEntity) {
		Optional<DonorEntity> donorEntity1 = donorRepository.findById(donorEntity.getId());
		if (donorEntity1.isPresent()) {
			donorRepository.save(donorEntity);
		} else {
			try {
				throw new DonorNotFoundException("Donor is not available to update with this " + donorEntity.getId());
			} catch (DonorNotFoundException e) {
				throw e;
			}
		}

	}

	@Override
	public void deleteDonor(Long id) {
		Optional<DonorEntity> findById = donorRepository.findById(id);
		if (findById.isEmpty()) {
			try {
				findById.orElseThrow(() -> new DonorNotFoundException("donar id is  not found to delete"));
			} catch (DonorNotFoundException e) {
				throw e;
			}
		} else {
			donorRepository.deleteById(id);
		}

	}

	@Override
	public List<DonorEntity> getAll() {
		List<DonorEntity> entities = donorRepository.findAll();
		return entities;
	}

	@Override
	public DonorBean getByUserId(Long id) {
		Optional<DonorEntity> optional = donorRepository.findById(id);
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(() -> new DonorNotFoundException("Donor Id Is Not Found"));
			} catch (DonorNotFoundException e) {
				throw e;
			}
		}
		DonorEntity donorEntity = optional.get();

		List<DonorDetailsEntity> donorEntities = donorEntity.getDonarDetails();
		List<DonorDetailsBean> donorBean = new ArrayList<>();
		
		for (DonorDetailsEntity donors : donorEntities) 
		{
			DonorDetailsBean detailstBean = DonorDetailsBean.builder()
					.donationId(donors.getDonationId())
					.bloodQuantity(donors.getBloodQuantity())
					.dateOfDonation(donors.getDateOfDonation())
					.status(donors.getStatus()).build();
			
			donorBean.add(detailstBean);
		}
		String Userurl = "http://localhost:8083/user/" + id;
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		ResponseEntity<UserBean> reponseEntity = restTemplate.exchange(Userurl, HttpMethod.GET, httpEntity,
				UserBean.class);
		
		UserBean userBean = reponseEntity.getBody();
		userBean.getBloodGroupId();
		userBean.getBloodBankId();
		
		DonorBean donorBean1 = DonorBean.builder().id(donorEntity.getId()).userId(userBean).donarDetails(donorBean).build();

		return donorBean1;
	}

	@Override
	public Long findDonorIdByUserId(Long userId) {
		  Long id = donorRepository.findIdByUserId(userId);
		return id;
	}

}