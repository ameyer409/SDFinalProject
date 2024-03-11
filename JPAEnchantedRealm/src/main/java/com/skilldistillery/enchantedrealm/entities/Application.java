package com.skilldistillery.enchantedrealm.entities;

import java.time.LocalDateTime;
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
import jakarta.persistence.ManyToOne;

@Entity
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String notes;
	@CreationTimestamp
	@Column(name="created_at")
	private LocalDateTime createdAt;
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn(name="job_posting_id")
	private JobPosting jobPosting;
	@ManyToOne
	@JoinColumn(name="applicant_id")
	private Applicant applicant;
	@ManyToOne
	@JoinColumn(name="application_status_id")
	private Status status;
	
	public Application() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public JobPosting getJobPosting() {
		return jobPosting;
	}

	public void setJobPosting(JobPosting jobPosting) {
		this.jobPosting = jobPosting;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
		Application other = (Application) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", notes=" + notes + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", jobPosting=" + jobPosting + ", applicant=" + applicant + ", status=" + status + "]";
	}
	
	
}
