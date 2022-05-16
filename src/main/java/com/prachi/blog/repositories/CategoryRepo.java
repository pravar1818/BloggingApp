package com.prachi.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prachi.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
}
