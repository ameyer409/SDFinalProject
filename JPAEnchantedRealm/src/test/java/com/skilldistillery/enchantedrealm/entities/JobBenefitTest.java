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

class JobBenefitTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private JobBenefit jobBenefit;

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
		jobBenefit = em.find(JobBenefit.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		jobBenefit = null;
	}
	
	@Test
	void test_JobBenefit_entity_mapping() {
		assertNotNull(jobBenefit);
		assertEquals(jobBenefit.getName(), "Medical Insurance");
	}
	
	@Test
	void test_JobBenefit_has_JobPostings() {
		assertFalse(jobBenefit.getJobPostings().isEmpty());
	}

}
