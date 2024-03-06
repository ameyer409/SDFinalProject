package com.skilldistillery.enchantedrealm.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String name;
	private String overview;
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	private boolean enabled;
	private String logo;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(mappedBy="company")
	private List<JobPosting> postings;
	
	public Company() {
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

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<JobPosting> getPostings() {
		return postings;
	}

	public void setPostings(List<JobPosting> postings) {
		this.postings = postings;
	}

	public void addJobPosting(JobPosting jobPosting) {
		if(postings == null) {
			postings = new ArrayList<>();
		}
		if(!postings.contains(jobPosting)) {
			postings.add(jobPosting);
			if(jobPosting.getCompany() != null) {
				jobPosting.getCompany().removeJobPosting(jobPosting);
			}
			jobPosting.setCompany(this);
		}
	}
	
	public void removeJobPosting(JobPosting jobPosting) {
		if(postings != null && postings.contains(jobPosting)) {
			postings.remove(jobPosting);
			jobPosting.setCompany(null);
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
		Company other = (Company) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", overview=" + overview + ", address=" + address + ", enabled="
				+ enabled + ", logo=" + logo + ", user=" + user + "]";
	}
	
	
}
