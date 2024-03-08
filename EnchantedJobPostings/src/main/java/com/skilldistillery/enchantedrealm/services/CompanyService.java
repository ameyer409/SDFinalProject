package com.skilldistillery.enchantedrealm.services;

import java.util.List;

import com.skilldistillery.enchantedrealm.entities.Application;
import com.skilldistillery.enchantedrealm.entities.Company;
import com.skilldistillery.enchantedrealm.entities.JobPosting;
import com.skilldistillery.enchantedrealm.entities.User;

public interface CompanyService {
	
	List<Company> findAllCompanies();
	
	List<JobPosting> findCompanyJobPostings();
	
	List<Application> findCompanyApplications();
 	
	Company addCompany(User user, Company company);
	
	Company editCompany(String username, int id, Company company);
	
	Company findById(int id);
	
	boolean deleteCompany(String username, int id);

	Company create(Company company, String name);

}
