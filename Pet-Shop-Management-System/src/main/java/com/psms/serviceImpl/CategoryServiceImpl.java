package com.psms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psms.entity.Category;
import com.psms.exception.ResourceNotFoundException;
import com.psms.model.CategoryDTO;
import com.psms.repository.CategoryRepository;
import com.psms.service.CategoryService;
import com.psms.util.CategoryConverter;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@Override
	public String createCategory(Category category) {
		String message= null;
		
		categoryRepository.save(category);
		if(category!=null)
		{
			message="Category details added successfully";
		}
		
		return message;
	}

	@Override
	public CategoryDTO getCategoryById(long categoryId) {
		Category category=categoryRepository.findById(categoryId).get();
		
		return categoryConverter.convertEntityToCategoryDTO(category);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> category=categoryRepository.findAll();
		List<CategoryDTO> categoryDTO= new ArrayList<>();
		for(Category c:category)
		{
			categoryDTO.add(categoryConverter.convertEntityToCategoryDTO(c));
		}
		return categoryDTO;
	}

	@Override
	public CategoryDTO updateCategory(long categoryId, Category category) {
		Category existingCategory = categoryRepository.findById(categoryId).get();
		
		categoryRepository.save(existingCategory);
		return categoryConverter.convertEntityToCategoryDTO(existingCategory);
	}

	@Override
	public String deleteCategoryById(long categoryId) {
		String message=null;
		Optional<Category> category = categoryRepository.findById(categoryId);
		
		if(category.isPresent())
		{
			categoryRepository.deleteById(categoryId);
			message="Catgoery deleted successfully";
		}
		else
		{
			throw new ResourceNotFoundException("Category","categoryId", categoryId);
		}
		return message;
	}

	@Override
	public void deleteAllCategory() {
		categoryRepository.deleteAll();
		
	}

	@Override
	public List<CategoryDTO> getCategoryByName(String categoryName) {
		List<Category>category=categoryRepository.findByName(categoryName);
		List<CategoryDTO> categoryDTO= new ArrayList<>();
		for(Category c: category)
		{
			categoryDTO.add(categoryConverter.convertEntityToCategoryDTO(c));
			
		}
		return categoryDTO;
	}
}
