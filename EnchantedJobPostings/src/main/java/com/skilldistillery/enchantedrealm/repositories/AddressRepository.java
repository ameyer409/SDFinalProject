package com.skilldistillery.enchantedrealm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enchantedrealm.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
