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

class StatusTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Status status;

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
		status = em.find(Status.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		status = null;
	}
	
	@Test
	void test_Status_entity_mappings() {
		assertNotNull(status);
		assertEquals(status.getName(), "Pending");
	}
	
	@Test
	void test_Status_has_applications() {
		assertFalse(status.getApplications().isEmpty());
	}

}
