package com.skilldistillery.enchantedrealm.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="job_posting")
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String title;
	private String description;
	@Column(name="minimum_salary")
	private int minimumSalary;
	@Column(name="maximum_salary")
	private int maximumSalary;
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDate createdAt;
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDate updatedAt;
	private int positions;
	private boolean enabled;
	private String requirements;
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	@ManyToOne
	@JoinColumn(name="industry_id")
	private Industry industry;
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	@JsonIgnore
	@OneToMany(mappedBy="jobPosting")
	private List<Application> apps;
	@ManyToMany(mappedBy="jobPostings")
	private List<JobBenefit> benefits;
	
	public JobPosting() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMinimumSalary() {
		return minimumSalary;
	}

	public void setMinimumSalary(int minimumSalary) {
		this.minimumSalary = minimumSalary;
	}

	public int getMaximumSalary() {
		return maximumSalary;
	}

	public void setMaximumSalary(int maximumSalary) {
		this.maximumSalary = maximumSalary;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getPositions() {
		return positions;
	}

	public void setPositions(int positions) {
		this.positions = positions;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Application> getApps() {
		return apps;
	}

	public void setApps(List<Application> apps) {
		this.apps = apps;
	}

	public List<JobBenefit> getJobBenefits() {
		return benefits;
	}

	public void setJobBenefits(List<JobBenefit> jobBenefits) {
		this.benefits = jobBenefits;
	}
	
	public void addApplication(Application application) {
		if(apps == null) {
			apps = new ArrayList<>();
		}
		if(!apps.contains(application)) {
			apps.add(application);
			if(application.getJobPosting() != null) {
				application.getJobPosting().removeApplication(application);
			}
			application.setJobPosting(this);
		}
	}
	
	public void removeApplication(Application application) {
		if(apps != null && apps.contains(application)) {
			apps.remove(application);
			application.setJobPosting(null);
		}
	}
	
	public void addJobBenefit(JobBenefit jobBenefit) {
		if(benefits == null) {
			benefits = new ArrayList<>();
		}
		if(!benefits.contains(jobBenefit)) {
			benefits.add(jobBenefit);
			jobBenefit.addJobPosting(this);
		}
	}
	
	public void removeJobBenefit(JobBenefit jobBenefit) {
		if(benefits != null && benefits.contains(jobBenefit)) {
			benefits.remove(jobBenefit);
			jobBenefit.removeJobPosting(this);
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
		JobPosting other = (JobPosting) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "JobPosting [id=" + id + ", title=" + title + ", description=" + description + ", minimumSalary="
				+ minimumSalary + ", maximumSalary=" + maximumSalary + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", positions=" + positions + ", enabled=" + enabled + ", requirements=" + requirements
				+ ", address=" + address + ", industry=" + industry + ", company=" + company + ", apps=" + apps
				+ ", jobBenefits=" + benefits + "]";
	}
	
	
	
}
