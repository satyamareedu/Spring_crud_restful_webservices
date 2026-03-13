package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	

}
