package com.skilldistillery.enchantedrealm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.enchantedrealm.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);

}
