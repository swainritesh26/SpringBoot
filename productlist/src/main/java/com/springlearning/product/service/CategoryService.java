package com.springlearning.product.service;

import com.springlearning.product.dto.CategoryDTO;
import com.springlearning.product.entity.Category;
import com.springlearning.product.exception.CategoryAlreadyExistsException;
import com.springlearning.product.mapper.CategoryMapper;
import com.springlearning.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    //create category
    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Optional<Category> optionalCategory =  categoryRepository.findByName(categoryDTO.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryAlreadyExistsException("Category " + categoryDTO.getName() + " Already Existed");
        }
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
