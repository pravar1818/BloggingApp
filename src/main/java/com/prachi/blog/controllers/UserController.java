package com.prachi.blog.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prachi.blog.config.AppConstants;
import com.prachi.blog.payloads.ApiResponse;
import com.prachi.blog.payloads.PostResponse;
import com.prachi.blog.payloads.UserDto;
import com.prachi.blog.payloads.UserResponse;
import com.prachi.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST - Create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	//PUT - Update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto updatedUser = userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	//DELETE - Delete User
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){
		userService.deleteUser(uid);
		return new ResponseEntity(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}
	
	//GET - Get User
	@GetMapping("/")
	public ResponseEntity<UserResponse> getAllUsers(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize){
		
		UserResponse userResponse = userService.getAllUsers(pageNumber,pageSize);
		return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
	}
	
	//GET - Get Single User
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
		return ResponseEntity.ok(userService.getUserById(userId));
	}
}
