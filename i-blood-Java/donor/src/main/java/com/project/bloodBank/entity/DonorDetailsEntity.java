package com.project.bloodBank.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "donor_details")
public class DonorDetailsEntity implements Serializable {
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long donationId;

	private Long bloodQuantity;

	private Date dateOfDonation;

	private Long donorId;

	private String status;

}
