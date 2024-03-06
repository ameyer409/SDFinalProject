package com.skilldistillery.enchantedrealm.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class ApplicantTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Applicant applicant;

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
		applicant = em.find(Applicant.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		applicant = null;
	}
	
	@Test
	void test_Applicant_entity_mapping() {
		assertNotNull(applicant);
		assertEquals("Willy", applicant.getFirstName());
	}
	
	@Test
	void test_Applicant_has_applications() {
		assertNotNull(applicant.getApplications());
		assertFalse(applicant.getApplications().isEmpty());
	}
	
	@Test
	void test_Applicant_has_Address() {
		assertNotNull(applicant.getAddress());
		assertEquals(applicant.getAddress().getStreet(), "Willy Wonka Way");
	}

	@Test
	void test_Applicant_has_User() {
		assertNotNull(applicant.getUser());
		assertEquals(applicant.getUser().getUsername(), "foundinguser");
	}

}
