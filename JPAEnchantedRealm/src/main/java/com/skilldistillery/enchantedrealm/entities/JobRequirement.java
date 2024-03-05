package com.skilldistillery.enchantedrealm.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="job_requirement")
public class JobRequirement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String name;
	@ManyToMany(mappedBy="jobRequirements")
	private List<JobPosting> jobPostings;
}
