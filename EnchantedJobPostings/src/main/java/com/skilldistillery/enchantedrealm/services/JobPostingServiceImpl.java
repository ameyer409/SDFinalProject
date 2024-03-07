package com.skilldistillery.enchantedrealm.services;

import java.util.List;
import java.util.Optional;

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
	public List<JobPosting> findByTitle(String title) {
		return jpRepo.findByTitleLike("%" + title + "%");
	}

	@Override
	public List<JobPosting> findByCompany(String name) {
		return jpRepo.findByCompany_nameLike("%" + name + "%");
	}

	@Override
	public List<JobPosting> findBySalary(int low, int high) {
		return jpRepo.findByMinimumSalaryGreaterThanEqualAndMaximumSalaryLessThanEqual(low, high);
	}

	@Override
	public List<JobPosting> findByIndustry(String name) {
		return jpRepo.findByIndustry_name(name);
	}

	@Override
	public List<JobPosting> findByCity(String city) {
		return jpRepo.findByAddress_city(city);
	}

	@Override
	public List<JobPosting> findByState(String state) {
		return jpRepo.findByAddress_state(state);
	}

	@Override
	public List<JobPosting> findByZipCode(String zip) {
		return jpRepo.findByAddress_zipCode(zip);
	}

	@Override
	public List<JobPosting> findByCityAndState(String city, String state) {
		return jpRepo.findByAddress_cityAndAddress_state(city, state);
	}

	@Override
	public JobPosting findById(int id) {
		Optional<JobPosting> jpOpt = jpRepo.findById(id);
		JobPosting jp = null;
		if(jpOpt.isPresent()) {
			jp = jpOpt.get();
		}
		return jp;
	}
	
	
}
