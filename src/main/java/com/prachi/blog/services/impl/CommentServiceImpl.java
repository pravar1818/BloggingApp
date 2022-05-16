package com.prachi.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prachi.blog.entities.Comment;
import com.prachi.blog.entities.Post;
import com.prachi.blog.entities.User;
import com.prachi.blog.exceptions.ResourceNotFoundException;
import com.prachi.blog.payloads.CommentDto;
import com.prachi.blog.repositories.CommentRepo;
import com.prachi.blog.repositories.PostRepo;
import com.prachi.blog.repositories.UserRepo;
import com.prachi.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
		// TODO Auto-generated method stub
		
		Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException());
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException());
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = commentRepo.save(comment);
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "CommentId", commentId));
		commentRepo.delete(comment);
	}

}
