package com.psms.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.psms.entity.Category;
import com.psms.model.CategoryDTO;
import com.psms.repository.CategoryRepository;
import com.psms.service.CategoryService;
import com.psms.util.CategoryConverter;

@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@MockBean
	private CategoryRepository categoryRepository;
	
	@Test
	void testCreateCategory()
	{
		Category category=Category.builder().categoryName("Dog").build();
		
		Mockito.when(categoryRepository.save(category)).thenReturn(category);
		assertThat(categoryService.createCategory(category)).isEqualTo("Category details added successfully");
		
	}
		
	@Test
	void testGetAllCategory()
	{
		Category category1 = Category.builder().categoryName("Dog").build();
		Category category2 = Category.builder().categoryName("Cat").build();
			
		List<Category>list= new ArrayList<>();
		list.add(category1);
		list.add(category2);
			
		Mockito.when(categoryRepository.findAll()).thenReturn(list);
			
		List<CategoryDTO> dto= categoryService.getAllCategory();
			
		List<Category> categorys= new ArrayList<>();
		dto.forEach(categoryDto->
		categorys.add(categoryConverter.convertDTOToCategory(categoryDto))
		);
		assertThat(categorys.get(0).getCategoryName()).isEqualTo(list.get(0).getCategoryName());
			
	}
	
	@Test
	void testUpdateCategory()
	{
		Category category = Category.builder().categoryName("Dog").build();
			
		Optional<Category> opCategory = Optional.of(category);
			
		Mockito.when(categoryRepository.findById(category.getCategoryId())).thenReturn(opCategory);
		
		Category c = opCategory.get();
		category.setCategoryName("Cat");
			
		Mockito.when(categoryRepository.save(category)).thenReturn(c);
			
		CategoryDTO dto=categoryService.updateCategory(category.getCategoryId(),category);
		assertEquals(dto.getCategoryName(),c.getCategoryName());
	}
}
