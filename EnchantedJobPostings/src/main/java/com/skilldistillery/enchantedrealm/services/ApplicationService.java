package com.skilldistillery.enchantedrealm.services;

import java.util.List;

import com.skilldistillery.enchantedrealm.entities.Applicant;
import com.skilldistillery.enchantedrealm.entities.Application;
import com.skilldistillery.enchantedrealm.entities.Company;

public interface ApplicationService  {

	List<Application> findByJobPostingId(String username, Company company, int id);

	List<Application> getAll(String name);

	Application findById(String name, int id);

	Application create(Application application, int id, String name);

	Application update(Application application, String name, int id);

	boolean deleteApplication(String name, int id);
}
