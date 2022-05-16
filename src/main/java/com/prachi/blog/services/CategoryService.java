package com.prachi.blog.services;

import java.util.List;

import com.prachi.blog.entities.Category;
import com.prachi.blog.payloads.CategoryDto;
import com.prachi.blog.payloads.CategoryResponse;

public interface CategoryService {
	
	//create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//get
	public CategoryDto getCategory(Integer categoryId);
	
	//getAll
	public CategoryResponse getCategories(Integer pageNumber, Integer pageSize);
}
