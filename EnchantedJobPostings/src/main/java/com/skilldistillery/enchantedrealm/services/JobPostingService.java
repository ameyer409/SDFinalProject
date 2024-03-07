package com.skilldistillery.enchantedrealm.services;

import java.util.List;

import com.skilldistillery.enchantedrealm.entities.JobPosting;

public interface JobPostingService {

	public List<JobPosting> index();
	
	public List<JobPosting> findByCompany(String name);
	
	public List<JobPosting> findBySalary(int low, int high);
	
	public List<JobPosting> findByIndustry(String name);
}
