package com.prachi.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.prachi.blog.entities.Category;
import com.prachi.blog.entities.Post;
import com.prachi.blog.entities.User;
import com.prachi.blog.exceptions.ResourceNotFoundException;
import com.prachi.blog.payloads.PostDto;
import com.prachi.blog.payloads.PostResponse;
import com.prachi.blog.repositories.CategoryRepo;
import com.prachi.blog.repositories.PostRepo;
import com.prachi.blog.repositories.UserRepo;
import com.prachi.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ", "User Id ", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", categoryId));
		
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost = postRepo.save(post);
		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "postId ", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "postId ", postId));
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort = null;
		
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = postRepo.findAll(p);
		
		List<Post> allPosts = pagePost.getContent(); 
		
		List<PostDto> postDtos = allPosts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "postId ", postId));
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
		
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ", "User Id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;

	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts = postRepo.findByTitleContaining("%"+keyword+"%");
		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}


}
