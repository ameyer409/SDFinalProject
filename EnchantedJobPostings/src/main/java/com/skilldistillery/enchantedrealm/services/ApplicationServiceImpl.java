package com.skilldistillery.enchantedrealm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enchantedrealm.entities.Applicant;
import com.skilldistillery.enchantedrealm.entities.Application;
import com.skilldistillery.enchantedrealm.entities.Company;
import com.skilldistillery.enchantedrealm.entities.JobPosting;
import com.skilldistillery.enchantedrealm.entities.Status;
import com.skilldistillery.enchantedrealm.entities.User;
import com.skilldistillery.enchantedrealm.repositories.ApplicantRepository;
import com.skilldistillery.enchantedrealm.repositories.ApplicationRepository;
import com.skilldistillery.enchantedrealm.repositories.CompanyRepository;
import com.skilldistillery.enchantedrealm.repositories.JobPostingRepository;
import com.skilldistillery.enchantedrealm.repositories.UserRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	CompanyRepository companyRepo;

	@Autowired
	ApplicationRepository appRepo;
	
	@Autowired
	ApplicantRepository applRepo;
	
	@Autowired
	JobPostingRepository jobRepo;
	
	@Autowired
	JobPostingService jobServ;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public List<Application> findByJobPostingId(String username, Company company, int id) {
		List<Application> apps = null;
		if (company.getUser().getUsername().equals(username)) {
			JobPosting jobPost = jobServ.findById(id);
			if(company.getPostings().contains(jobPost)) {
				apps = appRepo.findByJobPosting_id(id);
			}
		}
		return apps;
	}

	@Override
	public List<Application> getAll(String name) {
		// TODO Auto-generated method stub
		List<Application> apps = null;
		User user = userRepo.findByUsername(name);
		if(user != null) {
			apps = appRepo.findByApplicant_User_username(name);
		}
		return apps;
	}

	@Override
	public Application findById(String name, int id) {
		Application app;
		User user = userRepo.findByUsername(name);
		if(user != null) {
			return appRepo.findByIdAndApplicant_User_username(id, name);
		}
		return null;
	}

	@Override
	public Application create(Application application, int id, String name) {
		User user = userRepo.findByUsername(name);
		if(user != null) {
			Applicant applicant = applRepo.findByIdAndUser_username(id, name);
			application.setApplicant(applicant);
			Status status = new Status();
			status.setId(1);
			application.setStatus(status);
			return appRepo.saveAndFlush(application);
		}	
		return null;
	}

}
