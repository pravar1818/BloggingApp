package com.prachi.blog.repositories;

import com.prachi.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
