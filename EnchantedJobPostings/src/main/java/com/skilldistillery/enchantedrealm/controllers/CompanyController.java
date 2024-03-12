package com.skilldistillery.enchantedrealm.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enchantedrealm.entities.Applicant;
import com.skilldistillery.enchantedrealm.entities.Application;
import com.skilldistillery.enchantedrealm.entities.Company;
import com.skilldistillery.enchantedrealm.entities.JobPosting;
import com.skilldistillery.enchantedrealm.entities.User;
import com.skilldistillery.enchantedrealm.services.ApplicationService;
import com.skilldistillery.enchantedrealm.services.CompanyService;
import com.skilldistillery.enchantedrealm.services.JobPostingService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class CompanyController {
	
	@Autowired
	CompanyService companyServ;
	
	@Autowired
	JobPostingService jobServ;
	
	@Autowired
	ApplicationService appServ;
	
	@GetMapping("companies")
	public List<Company> index(){
		return companyServ.findAllCompanies();
	}
	
	@GetMapping("companies/{id}")
	public Company showCompanyDetails(@PathVariable("id") int id) {
		return companyServ.findById(id);
	}
	
	@PutMapping("companies/{id}")
	public Company editCompanyDetails(@PathVariable("id") int id, @RequestBody Company company, Principal principal, HttpServletResponse res, HttpServletRequest req) {
		Company updatedCompany;
		try {
			updatedCompany = companyServ.editCompany(principal.getName(), id, company);
			if(updatedCompany == null) {
				res.setStatus(404);
			} else {
				res.setStatus(200);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			updatedCompany = null;
		}
		return updatedCompany;
	}
	
	@PutMapping("companies/{id}/disable")
	public boolean disableCompany(@PathVariable("id") int id,  Principal principal, HttpServletResponse res, HttpServletRequest req) {
		boolean result = false;
		try {
			result = companyServ.deleteCompany(principal.getName(), id);  
			if(result == false) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(401);
		}
		return result;
	}
	
	@GetMapping("companies/{id}/jobpostings/{jobId}/applications")
	public List<Application> getCompanyJobPostingApplications(@PathVariable("id") int id, @PathVariable("jobId") int jobId, Principal principal, HttpServletRequest req, HttpServletResponse res){
		Company company = companyServ.findById(id);
		List<Application> jobApps = appServ.findByJobPostingId(principal.getName(),company,jobId);
		return jobApps;
	}

	@GetMapping("companies/{id}/jobpostings")
	public List<JobPosting> getCompanyJobPostings(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse res){
		List<JobPosting> jobPosts = null;
		Company company = companyServ.findById(id);
		if(company != null) {
			jobPosts = jobServ.findByCompanyId(id);	
		}
		return jobPosts;
	}
	
	@PostMapping("companies")
	public Company createCompany(@RequestBody Company company, Principal principal, HttpServletResponse res, HttpServletRequest req) {
		Company createdCompany;
		try {
			createdCompany = companyServ.create(company, principal.getName());
			res.setStatus(201);
			res.setHeader("Location", req.getRequestURL().append("/").append(createdCompany.getId()).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			res.setStatus(400);
			createdCompany = null;
		}
		return createdCompany;
	}
	
	@GetMapping("companies/company")
	public Company getCompany(Principal principal, HttpServletRequest req, HttpServletResponse res) {
		Company company;
		try {
			company = companyServ.findByUsername(principal.getName());
			res.setStatus(201);
			res.setHeader("Location", req.getRequestURL().append("/").append(company.getId()).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			res.setStatus(400);
			company = null;
		}
		return company;
	}
	
	@PostMapping("companies/{id}/jobpostings")
	public JobPosting creatCompanyJobPosting(@RequestBody JobPosting jobPost, @PathVariable("id") int id, Principal principal, HttpServletRequest req, HttpServletResponse res) {
		JobPosting createdJobPosting;
		try {
			createdJobPosting = jobServ.createJobPosting(id, jobPost);
			res.setStatus(201);
			res.setHeader("Location", req.getRequestURL().append("/").append(createdJobPosting.getId()).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			res.setStatus(400);
			createdJobPosting = null;
		}
		return createdJobPosting;
	}
	
}
