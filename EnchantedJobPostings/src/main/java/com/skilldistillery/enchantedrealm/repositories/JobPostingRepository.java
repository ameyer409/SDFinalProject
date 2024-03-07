package com.skilldistillery.enchantedrealm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enchantedrealm.entities.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer>{

	List<JobPosting> findByCompany_name(String company);
	
	List<JobPosting> findByMinimumSalaryGreaterThanEqualAndMaximumSalaryLessThanEqual(int lowest, int highest);
	
	List<JobPosting> findByMinimumSalaryGreaterThanEqual(int low);
	
	List<JobPosting> findByIndustry_name(String name);
	
	List<JobPosting> findByAddress_city(String city);
	List<JobPosting> findByAddress_state(String state);
	List<JobPosting> findByAddress_zipCode(String zip);
	List<JobPosting> findByAddress_cityAndAddress_state(String city, String state);
}
