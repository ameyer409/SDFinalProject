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

class CompanyTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Company comp;

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
		comp = em.find(Company.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comp = null;
	}
	
	@Test
	void test_Company_entity_mapping() {
		assertNotNull(comp);
		assertEquals(comp.getName(), "MediLife Solutions");
	}
	
	@Test
	void test_Company_has_user() {
		assertNotNull(comp.getUser());
		assertEquals(comp.getUser().getUsername(), "foundingcompany");
	}
	
	@Test
	void test_Company_has_JobPostings() {
		assertFalse(comp.getPostings().isEmpty());
	}
	
	@Test
	void test_Company_has_Address() {
		assertNotNull(comp.getAddress());
		assertEquals(comp.getAddress().getStreet(), "1234 First St");
	}

}
