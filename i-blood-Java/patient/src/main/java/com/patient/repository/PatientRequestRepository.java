package com.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.patient.bean.PatientRequestBean;
import com.patient.entity.PatientRequest;

public interface PatientRequestRepository extends JpaRepository<PatientRequest, Long>{

	@Query("SELECT new com.patient.bean.PatientRequestBean(A.patientRequestId, D.bloodType,C.firstName, A.patientRequestUnits, A.patientRequestOnDate, A.patientMedicalCondition,A.status) " +
		       "FROM PatientRequest A " +
		       "JOIN Patient B ON A.patientId = B.patientId " +
		       "JOIN UserEntity C ON C.userId = B.userId " +
		       "JOIN BloodGroupEntity D ON D.bloodId = C.bloodGroupId " +
		       "WHERE A.patientRequestId = :patientId")
	PatientRequestBean getBloodGroup(@Param("patientId") Long patientId);

	
	@Query(value = "SELECT C.firstName,D.bloodType"
			+ " FROM PatientRequest A "
			+ "JOIN Patient B ON A.patientId = B.patientId " + "JOIN UserEntity C ON C.userId = B.userId "
			+ "JOIN BloodGroupEntity D ON D.bloodId = C.bloodGroupId where A.patientRequestId =:patientId")
	String getBloodGroupType(@Param("patientId") Long patientId);

}





