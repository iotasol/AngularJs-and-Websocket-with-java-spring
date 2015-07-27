package com.demo.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}