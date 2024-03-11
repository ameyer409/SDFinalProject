package com.skilldistillery.enchantedrealm.controllers;

import java.security.Principal;

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
import com.skilldistillery.enchantedrealm.services.ApplicantService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class ApplicantController {
	
	@Autowired
	ApplicantService appServ;
	
	@GetMapping("applicants/applicant")
	public Applicant getApplicantByUser(Principal principal, HttpServletResponse res, HttpServletRequest req) {
		Applicant applicant = appServ.findByUsername(principal.getName());
		if(applicant == null) {
			res.setStatus(404);
		}
		return applicant;
	}
	
	@GetMapping("applicants/{id}")
	public Applicant getApplicant(@PathVariable("id") int id, Principal principal, HttpServletResponse res, HttpServletRequest req) {
		Applicant applicant = appServ.findById(principal.getName(),id);
		if(applicant == null) {
			res.setStatus(404);
		}
		return applicant;
	}
	
	@PostMapping("applicants")
	public Applicant createApplicant(@RequestBody Applicant applicant, Principal principal, HttpServletResponse res, HttpServletRequest req) {
		Applicant createdApplicant;
		try {
			createdApplicant = appServ.create(applicant, principal.getName());
			res.setStatus(201);
			res.setHeader("Location", req.getRequestURL().append("/").append(createdApplicant.getId()).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			res.setStatus(400);
			createdApplicant = null;
		}
		return createdApplicant;
	}
	
	@PutMapping("applicants/{id}")
	public Applicant updateApplicant(@PathVariable("id") int id, @RequestBody Applicant applicant, Principal principal, HttpServletResponse res, HttpServletRequest req) {
		Applicant updatedApplicant;
		try {
			updatedApplicant = appServ.update(applicant, principal.getName(), id);
			res.setStatus(200);
			if(updatedApplicant == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			updatedApplicant = null;
		}
		return updatedApplicant;
	}
	
	@PutMapping("applicants/{id}/disable")
	public boolean disableApplicant(@PathVariable("id") int id, Principal principal, HttpServletRequest req, HttpServletResponse res) {
		boolean result = false;
		try {
			result = appServ.deleteApplicant(principal.getName(), id);  
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

}
