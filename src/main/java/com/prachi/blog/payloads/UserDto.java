package com.prachi.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name;
	
	@Email(message = "Email address is not valid!")
	@NotEmpty
	private String email;
	
	@NotEmpty
	@Size(min = 4, max = 10, message = "Password must be minimum of 4 characters and maximum of 10 characters")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<CommentDto> comments = new HashSet<>();

}
