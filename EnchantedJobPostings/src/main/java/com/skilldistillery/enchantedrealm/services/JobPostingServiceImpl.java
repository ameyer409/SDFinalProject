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
	
}
