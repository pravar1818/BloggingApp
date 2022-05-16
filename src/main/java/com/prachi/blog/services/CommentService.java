package com.prachi.blog.services;



import com.prachi.blog.payloads.CommentDto;


public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
	
	void deleteComment(Integer commentId);
}
