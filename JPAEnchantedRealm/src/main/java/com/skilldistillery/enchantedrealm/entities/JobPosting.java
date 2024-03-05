package com.skilldistillery.enchantedrealm.entities;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="job_posting")
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	
	@Column(name="minimum_salary")
	private int minimumSalary;
	@Column(name="maximum_salary")
	private int maximumSalary;
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDate createdAt;
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDate updatedAt;
	@Column(name="work_type")
	private String workType;
	@Column(name="pay_type")
	private String payType;
	private int positions;
	@OneToOne
	@JoinColumn(name="location_id")
	private Location location;
	private String industry;
	private boolean isDisabled;
	@JsonIgnore
	@OneToMany(mappedBy="jobPosting")
	private List<Application> apps;
	@JsonIgnore
	@ManyToMany(mappedBy="jobPostings")
	private List<JobRequirement> jobRequirements;
	@JsonIgnore
	@ManyToMany(mappedBy="jobPostings")
	private List<JobRequirement> jobBenefits;
	
	
	public JobPosting() {
		super();
	}
	
	
	
}
