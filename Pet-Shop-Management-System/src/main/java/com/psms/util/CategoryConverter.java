package com.psms.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.psms.entity.Category;
import com.psms.model.CategoryDTO;

@Component
public class CategoryConverter {

	//converts from Category entity to CategoryDTO
		public CategoryDTO convertEntityToCategoryDTO(Category category)
		{
			CategoryDTO categoryDTO= new CategoryDTO();
			if(category!=null)
			{
				BeanUtils.copyProperties(category, categoryDTO);
			}
			return categoryDTO;
			
		}
		//converts from CategoryDTO to Category entity
		public Category convertDTOToCategory(CategoryDTO categoryDTO)
		{
			Category category = new Category();
			if(categoryDTO!=null)
			{
				BeanUtils.copyProperties(categoryDTO, category);
			}
			return category;
			
		}
}
