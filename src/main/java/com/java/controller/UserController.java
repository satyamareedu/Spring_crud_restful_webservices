package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.entity.User;
import com.java.exception.ResourceNotFoundException;
import com.java.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepository useRepository;
	
	//get all users
	@GetMapping
	public List<User> getAllUsers()
	{
		return this.useRepository.findAll();
	}
	
	
	// get user by id
	@GetMapping("/{id}")
	public User getuserbyId(@PathVariable(value="id") int userId)
	{
	return this.useRepository.findById(userId)
			.orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+ userId));	
	}
	
	
	//create user 
	@PostMapping
	public User createUser(@RequestBody User user)
	{
		return this.useRepository.save(user);
	}
    
	//update User
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable ("id") int userId)
	{
		User existing=this.useRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("USer not found with id:"+ userId));
		       existing.setFname(user.getFname());
		       existing.setLname(user.getLname());
		       existing.setEmail(user.getEmail());
		       return  this.useRepository.save(existing);
		      
	}
	
	
	// delete user by id
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int userId)
	{
		User existing=this.useRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("USer not found with id:"+ userId));
		      this.useRepository.delete(existing);
		      return ResponseEntity.ok().build();
	}
	
}
