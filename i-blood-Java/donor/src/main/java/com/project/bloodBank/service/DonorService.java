package com.project.bloodBank.service;

import java.util.List;

import com.project.bloodBank.bean.DonorBean;
import com.project.bloodBank.entity.DonorEntity;

public interface DonorService {

	DonorEntity saveDonor(DonorEntity donorEntity);

	DonorEntity getByDonorId(Long id);

	void updateDonor(DonorEntity DonorEntity);

	void deleteDonor(Long id);

	List<DonorEntity> getAll();
	
	DonorBean getByUserId(Long id);

	Long findDonorIdByUserId(Long userId);
	
}
