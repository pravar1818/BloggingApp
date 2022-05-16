package com.prachi.blog.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prachi.blog.config.AppConstants;
import com.prachi.blog.payloads.ApiResponse;
import com.prachi.blog.payloads.CategoryDto;
import com.prachi.blog.payloads.CategoryResponse;
import com.prachi.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdCategory = categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("catId") Integer categoryId){
		CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully!!", true), HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer categoryId){
		CategoryDto categoryDto = categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
	
	//get all
	@GetMapping("/")
	public ResponseEntity<CategoryResponse> getCategories(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize){
		CategoryResponse categoryResponse = categoryService.getCategories(pageNumber,pageSize);
		return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
	}
}
