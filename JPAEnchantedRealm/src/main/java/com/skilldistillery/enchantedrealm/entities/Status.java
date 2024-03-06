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
import jakarta.persistence.Table;

@Entity
@Table(name="application_status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy="status")
	private List<Application> applications;
	
	public Status() {
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
	
	public List<Application> getApplications() {
		return applications;
	}
	
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public void addApplication(Application application) {
		if(applications == null) {
			applications = new ArrayList<>();
		}
		if(!applications.contains(application)) {
			applications.add(application);
			if(application.getStatus() != null) {
				application.getStatus().removeApplication(application);
			}
			application.setStatus(this);
		}
	}
	
	public void removeApplication(Application application) {
		if(applications != null && applications.contains(application)) {
			applications.remove(application);
			application.setStatus(null);
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
		Status other = (Status) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", name=" + name + ", applications=" + applications + "]";
	}
	
	
}
