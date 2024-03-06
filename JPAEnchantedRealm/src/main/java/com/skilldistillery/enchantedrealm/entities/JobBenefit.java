package com.skilldistillery.enchantedrealm.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="job_benefit")
public class JobBenefit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String name;
//	@JsonIgnore
	@ManyToMany(mappedBy="benefits")
	private List<JobPosting> postings;
	
	public JobBenefit() {
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
		return postings;
	}

	public void setJobPostings(List<JobPosting> jobPostings) {
		this.postings = jobPostings;
	}
	
	public void addJobPosting(JobPosting jobPosting) {
		if(postings == null) {
			postings = new ArrayList<>();
		}
		if(!postings.contains(jobPosting)) {
			postings.add(jobPosting);
			jobPosting.addJobBenefit(this);
		}
	}
	
	public void removeJobPosting(JobPosting jobPosting) {
		if(postings != null && postings.contains(jobPosting)) {
			postings.remove(jobPosting);
			jobPosting.removeJobBenefit(this);
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
		JobBenefit other = (JobBenefit) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "JobBenefit [id=" + id + ", name=" + name + ", jobPostings=" + postings + "]";
	}
	
	
}
