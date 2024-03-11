package com.skilldistillery.enchantedrealm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.enchantedrealm.entities.Applicant;
import com.skilldistillery.enchantedrealm.entities.Application;
import com.skilldistillery.enchantedrealm.entities.User;
import com.skilldistillery.enchantedrealm.repositories.ApplicantRepository;
import com.skilldistillery.enchantedrealm.repositories.ApplicationRepository;
import com.skilldistillery.enchantedrealm.repositories.UserRepository;

@Service
public class ApplicantServiceImpl implements ApplicantService{

	@Autowired
	ApplicantRepository appRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ApplicationRepository applicationRepo;
	
	@Override
	public Applicant findById(String username, int id) {
		User user = userRepo.findByUsername(username);
		if(user != null) {	
			return appRepo.findByIdAndUser_username(id, username);
		}
		return null;
	}

	@Override
	public Applicant create(Applicant applicant, String username) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(username);
		if(user != null) {
			applicant.setUser(user);
			return appRepo.saveAndFlush(applicant);
		}
		
		return null;
	}

	@Override
	public Applicant update(Applicant applicant, String name, int id) {
		User user = userRepo.findByUsername(name);
		if(user != null) {
			Applicant managedApplicant = appRepo.findByIdAndUser_username(id, name);
			managedApplicant.setAboutMe(applicant.getAboutMe());
			managedApplicant.setAddress(applicant.getAddress());
			managedApplicant.setDateOfBirth(applicant.getDateOfBirth());
			managedApplicant.setEligibleToWork(applicant.isEligibleToWork());
			managedApplicant.setEmail(applicant.getEmail());
			managedApplicant.setFirstName(applicant.getFirstName());
			managedApplicant.setLastName(applicant.getLastName());
			managedApplicant.setProfileImg(applicant.getProfileImg());
			managedApplicant.setRelocation(applicant.isRelocation());
			return appRepo.saveAndFlush(managedApplicant);
		}
		return null;
	}

	@Override
	public boolean deleteApplicant(String name, int id) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(name);
		if(user != null) {
			Applicant applicant = appRepo.findByIdAndUser_username(id, name);
			List<Application> applications = applicant.getApplications();
			for (Application application : applications) {
				applicationRepo.deleteById(application.getId());
			}
			applications = null;
			appRepo.saveAndFlush(applicant);
			user.setEnabled(false);
			userRepo.saveAndFlush(user);
			return true;
		}
		return false;
	}

	@Override
	public Applicant findByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if(user != null) {	
			return appRepo.findByUser_username(username);
		}
		return null;
	
	}

}
