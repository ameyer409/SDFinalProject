package com.skilldistillery.enchantedrealm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enchantedrealm.entities.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer>{

}
