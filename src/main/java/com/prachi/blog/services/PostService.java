package com.prachi.blog.services;

import java.util.List;

import com.prachi.blog.entities.Post;
import com.prachi.blog.payloads.PostDto;
import com.prachi.blog.payloads.PostResponse;

public interface PostService {
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	public PostDto updatePost(PostDto postDto, Integer postId);
	public void deletePost(Integer postId);
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	public PostDto getPostById(Integer postId);
	public List<PostDto> getPostsByCategory(Integer categoryId);
	public List<PostDto> getPostsByUser(Integer userId);
	public List<PostDto> searchPosts(String keyword);
}
