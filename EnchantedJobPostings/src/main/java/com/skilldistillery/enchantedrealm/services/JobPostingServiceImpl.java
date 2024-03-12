package com.skilldistillery.enchantedrealm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enchantedrealm.entities.Address;
import com.skilldistillery.enchantedrealm.entities.Company;
import com.skilldistillery.enchantedrealm.entities.Industry;
import com.skilldistillery.enchantedrealm.entities.JobPosting;
import com.skilldistillery.enchantedrealm.repositories.AddressRepository;
import com.skilldistillery.enchantedrealm.repositories.CompanyRepository;
import com.skilldistillery.enchantedrealm.repositories.JobPostingRepository;

@Service
public class JobPostingServiceImpl implements JobPostingService{
	
	@Autowired
	JobPostingRepository jpRepo;
	
	@Autowired
	CompanyRepository companyRepo;
	
	@Autowired
	AddressRepository addRepo;
	
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

	@Override
	public List<JobPosting> findByCompanyId(int id) {
		return jpRepo.findByCompany_id(id);
	}

	@Override
	public JobPosting createJobPosting(int id, JobPosting jobPost) {
		Company company = companyRepo.findById(id);
		if(company != null) {
			Address address = jobPost.getAddress();
			addRepo.save(address);
			jobPost.setCompany(company);
			jobPost.setAddress(address);
			Industry industry = new Industry();
			industry.setId(1);
			jobPost.setIndustry(industry);
			return jpRepo.saveAndFlush(jobPost);	
		}
		return null;
	}
	
	
}
