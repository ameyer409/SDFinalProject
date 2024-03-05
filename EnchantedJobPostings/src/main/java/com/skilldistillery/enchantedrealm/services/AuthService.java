package com.skilldistillery.enchantedrealm.services;

import com.skilldistillery.enchantedrealm.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);
}
