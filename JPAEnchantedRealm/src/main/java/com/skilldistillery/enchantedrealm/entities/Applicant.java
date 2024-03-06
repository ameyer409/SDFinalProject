package com.skilldistillery.enchantedrealm.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="birthdate")
	private LocalDate dateOfBirth;
	private String email;
	@Column(name="eligible_to_work")
	private boolean eligibleToWork;
	private boolean relocation;
	@Column(name="profile_img")
	private String profileImg;
	@UpdateTimestamp
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	@Column(name="about_me")
	private String aboutMe;
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	@OneToMany(mappedBy="applicant")
	private List<Application> applications;
	
	public Applicant() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEligibleToWork() {
		return eligibleToWork;
	}

	public void setEligibleToWork(boolean eligibleToWork) {
		this.eligibleToWork = eligibleToWork;
	}

	public boolean isRelocation() {
		return relocation;
	}

	public void setRelocation(boolean relocation) {
		this.relocation = relocation;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void addApplication(Application application) {
		if(applications == null) {
			applications = new ArrayList<>();
		}
		if(!applications.contains(application)) {
			applications.add(application);
			if(application.getApplicant() != null) {
				application.getApplicant().removeApplication(application);
			}
			application.setApplicant(this);
		}
	}
	
	public void removeApplication(Application application) {
		if(applications != null && applications.contains(application)) {
			applications.remove(application);
			application.setApplicant(null);
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
		Applicant other = (Applicant) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Applicant [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", email=" + email + ", eligibleToWork=" + eligibleToWork + ", relocation=" + relocation
				+ ", profileImg=" + profileImg + ", updatedAt=" + updatedAt + ", aboutMe=" + aboutMe + ", address="
				+ address + "]";
	}
	
	
}
