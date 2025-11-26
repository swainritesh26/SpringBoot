package com.springlearning.product.service;

import com.springlearning.product.dto.CategoryDTO;
import com.springlearning.product.entity.Category;
import com.springlearning.product.mapper.CategoryMapper;
import com.springlearning.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    //create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category = CategoryMapper.toCategoryEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toCategoryDTO(category);
    }
    //get all category
    //get category by id
    //delete category
}
