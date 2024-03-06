package com.skilldistillery.enchantedrealm.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class JobPostingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private JobPosting jobPosting;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAEnchantedRealm");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		jobPosting = em.find(JobPosting.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		jobPosting = null;
	}
	@Test
	void test_JobPosting_entity_mappings() {
		assertNotNull(jobPosting);
		assertEquals(jobPosting.getTitle(), "Research Scientist");
	}
	
	@Test
	void test_JobPosting_has_address() {
		assertNotNull(jobPosting.getAddress());
		assertEquals(jobPosting.getAddress().getStreet(), "Willy Wonka Way");
	}
	
	@Test
	void test_JobPosting_has_Industry() {
		assertNotNull(jobPosting.getIndustry());
		assertEquals(jobPosting.getIndustry().getName(), "Healthcare");
	}
	
	@Test
	void test_JobPosting_has_Company() {
		assertNotNull(jobPosting.getCompany());
		assertEquals(jobPosting.getCompany().getId(), 1);
	}
	
	@Test
	void test_JobPosting_has_applications() {
		assertFalse(jobPosting.getApps().isEmpty());
	}
	
	@Test
	void test_JobPosting_has_Benefits() {
		assertFalse(jobPosting.getBenefits().isEmpty());
	}

}
