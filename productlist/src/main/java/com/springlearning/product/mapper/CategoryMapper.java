package com.springlearning.product.mapper;

import com.springlearning.product.dto.CategoryDTO;
import com.springlearning.product.entity.Category;
import com.springlearning.product.entity.Product;

public class CategoryMapper {

    public static CategoryDTO toCategoryDTO(Category category){
        if(category == null){
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setProducts(category.getProducts().stream()
                .map(ProductMapper::toProductDTO).toList());
        return categoryDTO;
    }

    public static Category toCategoryEntity(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
