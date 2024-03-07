package com.skilldistillery.enchantedrealm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enchantedrealm.entities.JobPosting;
import com.skilldistillery.enchantedrealm.services.JobPostingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class JobPostingController {

	@Autowired
	JobPostingService jpService;
	
	@GetMapping("jobpostings")
	public List<JobPosting> index(HttpServletRequest req, HttpServletResponse res) {
		return jpService.index();
	}
	
	@GetMapping("jobpostings/{name}")
	public List<JobPosting> findByCompanyName(@PathVariable("name") String name, HttpServletRequest req, HttpServletResponse res) {
		return jpService.findByCompany(name);
	}
	
	@GetMapping("jobpostings/{low}/{high}")
	public List<JobPosting> findBySalaryRange(@PathVariable("low") int low, @PathVariable("high") int high, HttpServletRequest req, HttpServletResponse res) {
		return jpService.findBySalary(low, high);
	}
	
	@GetMapping("jobpostings/industry/{name}")
	public List<JobPosting> findByIndustry(@PathVariable("name") String name, HttpServletRequest req, HttpServletResponse res) {
		return jpService.findByIndustry(name);
	}
	
	@GetMapping("jobpostings/location/city/{city}")
	public List<JobPosting> findByCity(@PathVariable("city") String name, HttpServletRequest req, HttpServletResponse res) {
		return jpService.findByCity(name);
	}
	
	
	@GetMapping("jobpostings/location/state/{state}")
	public List<JobPosting> findByState(@PathVariable("state") String name, HttpServletRequest req, HttpServletResponse res) {
		return jpService.findByState(name);
	}
	
	
	@GetMapping("jobpostings/location/zipcode/{zip}")
	public List<JobPosting> findByZipcode(@PathVariable("zip") String zip, HttpServletRequest req, HttpServletResponse res) {
		return jpService.findByZipCode(zip);
	}
	
	
	@GetMapping("jobpostings/location/{city}/{state}")
	public List<JobPosting> findBycity(@PathVariable("city") String city, @PathVariable("state") String state, HttpServletRequest req, HttpServletResponse res) {
		return jpService.findByCityAndState(city, state);
	}
	
	
}
