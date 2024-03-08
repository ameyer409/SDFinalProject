package com.skilldistillery.enchantedrealm.services;

import com.skilldistillery.enchantedrealm.entities.Applicant;

public interface ApplicantService {
	
	Applicant findById(String username, int id);
	
	Applicant create(Applicant applicant, String username);

	Applicant update(Applicant applicant, String name, int id);

	boolean deleteApplicant(String name, int id);

}
