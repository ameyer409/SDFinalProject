package com.skilldistillery.enchantedrealm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enchantedrealm.entities.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{
	
	List<Application> findByJobPosting_id(int id);

	List<Application> findByApplicant_User_username(String name);

	Application findByIdAndApplicant_User_username(int id, String name);
	
}
