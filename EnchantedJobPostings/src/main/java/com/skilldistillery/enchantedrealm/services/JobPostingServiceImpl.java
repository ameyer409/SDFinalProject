package com.skilldistillery.enchantedrealm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enchantedrealm.entities.JobPosting;
import com.skilldistillery.enchantedrealm.repositories.JobPostingRepository;

@Service
public class JobPostingServiceImpl implements JobPostingService{
	
	@Autowired
	JobPostingRepository jpRepo;
	
	@Override
	public List<JobPosting> index() {
		return jpRepo.findAll();
	}

	@Override
	public List<JobPosting> findByCompany(String name) {
		return jpRepo.findByCompany_name(name);
	}

	@Override
	public List<JobPosting> findBySalary(int low, int high) {
		return jpRepo.findByMinimumSalaryGreaterThanEqualAndMaximumSalaryLessThanEqual(low, high);
	}

	@Override
	public List<JobPosting> findByIndustry(String name) {
		return jpRepo.findByIndustry_name(name);
	}
	
	
}
