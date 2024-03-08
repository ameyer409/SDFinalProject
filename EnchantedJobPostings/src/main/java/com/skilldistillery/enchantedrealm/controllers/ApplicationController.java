package com.skilldistillery.enchantedrealm.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.enchantedrealm.entities.Applicant;
import com.skilldistillery.enchantedrealm.entities.Application;
import com.skilldistillery.enchantedrealm.services.ApplicationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class ApplicationController {
	
	@Autowired
	ApplicationService appServ;
	
	
	@GetMapping("applications")
	public List<Application> getApplicantApplications(Principal principal, HttpServletResponse res, HttpServletRequest req){
		return appServ.getAll(principal.getName());
		
	}
	
	@GetMapping("applications/{id}")
	public Application getApplication(@PathVariable("id") int id, Principal principal, HttpServletResponse res, HttpServletRequest req) {
		Application app = appServ.findById(principal.getName(), id);
		if(app == null) {
			res.setStatus(404);
		}
		return app;
	}
	
	@PostMapping("applications/{id}")
	public Application createApplication(@RequestBody Application application, @PathVariable("id")int id, Principal principal, HttpServletResponse res, HttpServletRequest req) {
		Application createdApplication;
		try {
			createdApplication = appServ.create(application, id, principal.getName());
			res.setStatus(201);
			res.setHeader("Location", req.getRequestURL().append("/").append(createdApplication.getId()).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			res.setStatus(400);
			createdApplication = null;
		}
		return createdApplication;
	}

}
