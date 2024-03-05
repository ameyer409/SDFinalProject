package com.skilldistillery.enchantedrealm.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="legal_question")
public class LegalQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="legal_question_has_application",
	joinColumns= @JoinColumn(name="legal_questions_id"),
	inverseJoinColumns= @JoinColumn(name="application_id"))
	private List<Application> applications;
}
