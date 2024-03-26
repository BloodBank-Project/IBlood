package com.project.bloodBank.service;

import java.util.List;

import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.entity.DonorDetailsEntity;


public interface DonorDetailsService {

	DonorDetailsEntity saveDonation(DonorDetailsEntity donorEntity);

	DonorDetailsBean getByDonationId(Long id);

	void updateDonation(DonorDetailsEntity DonorEntity);

	void deleteDonation(Long id);

	List<DonorDetailsBean> getAll();
	
}
