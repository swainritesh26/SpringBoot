package com.springlearning.product.service;

import com.springlearning.product.dto.CategoryDTO;
import com.springlearning.product.entity.Category;
import com.springlearning.product.mapper.CategoryMapper;
import com.springlearning.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<CategoryDTO> getAllCategory(){
        return categoryRepository.findAll().stream().map(CategoryMapper ::toCategoryDTO).toList();
    }

    //get category by id
    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category Not Found!"));
        return CategoryMapper.toCategoryDTO(category);
    }

    //delete category
    public String deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return "Category " + id + " has been deleted! ";
    }
}
