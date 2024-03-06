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

class ApplicationTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Application app;

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
		app = em.find(Application.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		app = null;
	}
	
	@Test
	void test_Application_entity_mapping() {
		assertNotNull(app);
		assertEquals(app.getNotes(), "Hope I can get this job. it would be great!");
	}
	
	@Test
	void test_Application_has_applicant() {
		assertNotNull(app.getApplicant());
		assertEquals(app.getApplicant().getFirstName(), "Willy");
	}
	
	@Test
	void test_Application_has_JobPosting() {
		assertNotNull(app.getJobPosting());
		assertEquals(app.getJobPosting().getTitle(), "Research Scientist");
	}

	@Test
	void test_Application_has_Status() {
		assertNotNull(app.getStatus());
		assertEquals(app.getStatus().getName(), "Pending");
	}

}
