package com.prachi.blog.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prachi.blog.entities.Category;
import com.prachi.blog.entities.Post;
import com.prachi.blog.exceptions.ResourceNotFoundException;
import com.prachi.blog.payloads.CategoryDto;
import com.prachi.blog.payloads.CategoryResponse;
import com.prachi.blog.payloads.PostResponse;
import com.prachi.blog.repositories.CategoryRepo;
import com.prachi.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category = modelMapper.map(categoryDto, Category.class);
		Category addedCategory = categoryRepo.save(category);
		return modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category olderCategory = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", categoryId));
		olderCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		olderCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = categoryRepo.save(olderCategory);
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", categoryId));
		categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryResponse getCategories(Integer pageNumber, Integer pageSize) {
		// TODO Auto-generated method stub
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		Page<Category> pageCategory = categoryRepo.findAll(p);
		
		List<Category> allPosts = pageCategory.getContent(); 
		
		List<CategoryDto> categoriesDto = allPosts.stream().map((category) -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setContent(categoriesDto);
		categoryResponse.setPageNumber(pageCategory.getNumber());
		categoryResponse.setPageSize(pageCategory.getSize());
		categoryResponse.setTotalElements(pageCategory.getTotalElements());
		categoryResponse.setTotalPages(pageCategory.getTotalPages());
		categoryResponse.setLastPage(pageCategory.isLast());
		return categoryResponse;
	}

}
