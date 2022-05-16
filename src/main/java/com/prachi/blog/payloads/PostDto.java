package com.prachi.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.prachi.blog.entities.Comment;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	
	@NotNull
	@Size(min=10, max=200, message = "Min size of title is 4 characters and Max size of title is 200")
	private String title;
	
	@NotEmpty
	@Size(min=10, max=3000, message = "Min size of title is 10 characters and Max size of title is 3000")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;

	private UserDto user;

	private Set<CommentDto> comments = new HashSet<>();
	
}
