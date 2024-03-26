package com.project.bloodBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.bloodBank.bean.DonorDetailsBean;
import com.project.bloodBank.entity.DonorDetailsEntity;

public interface DonorDetailsRepository extends JpaRepository<DonorDetailsEntity, Long>{
	
	@Query("SELECT new com.project.bloodBank.bean.DonorDetailsBean(A.donationId, D.bloodType,C.firstName, A.bloodQuantity, A.dateOfDonation, A.status) " +
		       "FROM DonorDetailsEntity A " +
		       "JOIN DonorEntity B ON A.donorId = B.id " +
		       "JOIN UserEntity C ON C.userId = B.userId " +
		       "JOIN BloodGroupEntity D ON D.bloodId = C.bloodGroupId " +
		       "WHERE A.donationId = :donorId")
	DonorDetailsBean getBloodGroup(@Param("donorId") Long donorId);

	
	@Query(value = "SELECT C.firstName,D.bloodType"
			+ " FROM DonorDetailsEntity A "
			+ "JOIN DonorEntity B ON A.donorId = B.id " + "JOIN UserEntity C ON C.userId = B.userId "
			+ "JOIN BloodGroupEntity D ON D.bloodId = C.bloodGroupId where A.donationId =:donorId")
	String getBloodGroupType(@Param("donorId") Long donorId);
	
	
}
