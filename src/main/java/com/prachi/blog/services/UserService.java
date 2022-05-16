package com.prachi.blog.services;

import java.util.List;

import com.prachi.blog.payloads.UserDto;
import com.prachi.blog.payloads.UserResponse;

public interface UserService {
	
	public UserDto createUser(UserDto user);
	public UserDto updateUser(UserDto user, Integer userId);
	public UserDto getUserById(Integer userId);
	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize);
	public void deleteUser(Integer userId);


}
