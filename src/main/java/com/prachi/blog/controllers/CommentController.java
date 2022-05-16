package com.prachi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prachi.blog.services.CommentService;
import com.prachi.blog.entities.Comment;
import com.prachi.blog.payloads.ApiResponse;
import com.prachi.blog.payloads.CommentDto;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/post/{postId}/{userId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId, @PathVariable Integer userId){
		
		CommentDto createComment = commentService.createComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		
		commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!", true), HttpStatus.OK);
		
	}
}
