package com.skilldistillery.enchantedrealm.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Industry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy="industry")
	private List<JobPosting> jobPostings;
	
	public Industry() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<JobPosting> getJobPostings() {
		return jobPostings;
	}
	
	public void setJobPostings(List<JobPosting> jobPostings) {
		this.jobPostings = jobPostings;
	}
	
	public void addJobPostiong(JobPosting jobPosting) {
		if(jobPostings == null) {
			jobPostings = new ArrayList<>();
		}
		if(!jobPostings.contains(jobPosting)) {
			jobPostings.add(jobPosting);
			if(jobPosting.getIndustry() != null) {
				jobPosting.getIndustry().removeJobPosting(jobPosting);
			}
			jobPosting.setIndustry(this);
		}
	}
	
	public void removeJobPosting(JobPosting jobPosting) {
		if(jobPostings != null && jobPostings.contains(jobPosting)) {
			jobPostings.remove(jobPosting);
			jobPosting.setIndustry(null);
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Industry other = (Industry) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Industry [id=" + id + ", name=" + name + ", jobPostings=" + jobPostings + "]";
	}
	
	
}
