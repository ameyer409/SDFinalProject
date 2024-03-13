package com.skilldistillery.enchantedrealm.services;

import java.util.List;

import com.skilldistillery.enchantedrealm.entities.JobPosting;

public interface JobPostingService {

	public List<JobPosting> index();
	
	public List<JobPosting> findByCompany(String name);
	
	public List<JobPosting> findBySalary(int low, int high);
	
	public List<JobPosting> findByIndustry(String name);
	
	public List<JobPosting> findByCity(String city);
	
	public List<JobPosting> findByState(String state);
	
	public List<JobPosting> findByZipCode(String zip);
	
	public List<JobPosting> findByCityAndState(String city, String state);
	
	public JobPosting findById(int id);

	public List<JobPosting> findByTitle(String title);
	
	public List<JobPosting> findByCompanyId(int id);

	public JobPosting updateJobPosting(String username, int id, JobPosting jobPost);
	
	public JobPosting createJobPosting(int id , JobPosting jobPost);
}
