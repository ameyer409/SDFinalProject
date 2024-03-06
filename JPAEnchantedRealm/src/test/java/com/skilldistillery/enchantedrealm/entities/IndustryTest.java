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

class IndustryTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Industry industry;

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
		industry = em.find(Industry.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		industry = null;
	}
	
	@Test
	void test_Industry_entity_mapping() {
		assertNotNull(industry);
		assertEquals(industry.getName(), "Healthcare");
	}
	
	@Test
	void test_Industry_has_JobPostings() {
		assertFalse(industry.getJobPostings().isEmpty());
	}

}
