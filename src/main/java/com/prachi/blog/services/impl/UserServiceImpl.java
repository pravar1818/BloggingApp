package com.prachi.blog.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prachi.blog.entities.Category;
import com.prachi.blog.entities.User;
import com.prachi.blog.exceptions.ResourceNotFoundException;
import com.prachi.blog.payloads.CategoryResponse;
import com.prachi.blog.payloads.UserDto;
import com.prachi.blog.payloads.UserResponse;
import com.prachi.blog.repositories.UserRepo;
import com.prachi.blog.services.UserService;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		
		return this.userToDto(user);
	}

	@Override
	public UserResponse getAllUsers(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		Page<User> pageUser = userRepo.findAll(p);
		
		List<User> allUsers = pageUser.getContent(); 
		
		List<UserDto> userDtos = allUsers.stream().map(user -> userToDto(user)).collect(Collectors.toList());
		
		UserResponse userResponse = new UserResponse();
		userResponse.setContent(userDtos);
		userResponse.setPageNumber(pageUser.getNumber());
		userResponse.setPageSize(pageUser.getSize());
		userResponse.setTotalElements(pageUser.getTotalElements());
		userResponse.setTotalPages(pageUser.getTotalPages());
		userResponse.setLastPage(pageUser.isLast());
		return userResponse;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
		userRepo.delete(user);
	}

	public User dtoToUser(UserDto userDto) {
		//User user = new User();
		//user.setId(userDto.getId());
		//user.setName(userDto.getName());
		//user.setEmail(userDto.getEmail());
		//user.setAbout(userDto.getAbout());
		//user.setPassword(userDto.getPassword());
		return modelMapper.map(userDto, User.class);
	}
	
	public UserDto userToDto(User user) {
		//UserDto userDto = new UserDto();
		//userDto.setId(user.getId());
		//userDto.setName(user.getName());
		//userDto.setEmail(user.getEmail());
		//userDto.setAbout(user.getAbout());
		//userDto.setPassword(user.getPassword());
		return modelMapper.map(user, UserDto.class);
	}
}
