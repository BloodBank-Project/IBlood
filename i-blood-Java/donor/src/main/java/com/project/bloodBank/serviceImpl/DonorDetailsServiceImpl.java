package com.project.bloodBank.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.entity.DonorDetailsEntity;
import com.project.bloodBank.entity.DonorEntity;
import com.project.bloodBank.exceptions.DonationDetailsNotFoundException;
import com.project.bloodBank.repository.DonorDetailsRepository;
import com.project.bloodBank.service.DonorDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DonorDetailsServiceImpl implements DonorDetailsService {

	@Autowired
	private DonorDetailsRepository detailsRepository;

	@Override
	public DonorDetailsEntity saveDonation(DonorDetailsEntity donorEntity) {
		try {
			if (donorEntity.getDonorId() == null) {
	            throw new RuntimeException("DonorId is missing for the donation entity.");
	        }
			if (donorEntity.getDonationId() != null) {
	            DonorDetailsEntity existingEntity = detailsRepository.findById(donorEntity.getDonationId())
	                    .orElseThrow(() -> new DonationDetailsNotFoundException("Donation details not found"));

	            donorEntity.setDonorId(existingEntity.getDonorId());
	        }
			DonorDetailsEntity save = detailsRepository.save(donorEntity);
			return save;
		} catch (DonationDetailsNotFoundException e) {
			throw e;
		}
	}

	@Override
	public void updateDonation(DonorDetailsEntity donorEntity) {
		Optional<DonorDetailsEntity> donorEntity1 = detailsRepository.findById(donorEntity.getDonationId());
		if (donorEntity1.isPresent()) {
			DonorDetailsEntity detailsEntity = donorEntity1.get();
			donorEntity.setDonorId(detailsEntity.getDonorId());
			detailsRepository.save(donorEntity);
		} else {
			try {
				throw new DonationDetailsNotFoundException("Details are not found to update");
			} catch (DonationDetailsNotFoundException e) {
				throw e;
			}
		}
	}

	@Override
	public void deleteDonation(Long id) {
		Optional<DonorDetailsEntity> findById = detailsRepository.findById(id);
		if (findById.isEmpty()) {
			try {
				findById.orElseThrow(() -> new DonationDetailsNotFoundException("donar id is  not found to delete"));
			} catch (DonationDetailsNotFoundException e) {
				throw e;
			}
		} else {
			detailsRepository.deleteById(id);
		}
	}

	@Override
	public List<DonorDetailsBean> getAll() {
		List<DonorDetailsEntity> entities = detailsRepository.findAll();
		List<DonorDetailsBean> beans = new ArrayList<>();

		for (DonorDetailsEntity donorDetailsEntity : entities) {
			Long donarId = donorDetailsEntity.getDonationId();
			String bloodGroupRepo = detailsRepository.getBloodGroupType(donarId);
			DonorDetailsBean bloodGroupBean = detailsRepository.getBloodGroup(donarId);
			beans.add(bloodGroupBean);
		}

		return beans;
	}

	@Override
	public DonorDetailsBean getByDonationId(Long id) {
		Optional<DonorDetailsEntity> optional = detailsRepository.findById(id);

		DonorDetailsBean bloodGroupBean = new DonorDetailsBean();
		if (optional.isEmpty()) {
			try {
				optional.orElseThrow(
						() -> new DonationDetailsNotFoundException("donor is not found to delete with this " + id));
			} catch (DonationDetailsNotFoundException e) {
				throw e;
			}
		} else {
			Long donationId = optional.get().getDonationId();
			String bloodGroupRepo = detailsRepository.getBloodGroupType(donationId);
			bloodGroupBean = detailsRepository.getBloodGroup(donationId);
			System.out.println(bloodGroupBean);
		}
		return bloodGroupBean;

	}

}
