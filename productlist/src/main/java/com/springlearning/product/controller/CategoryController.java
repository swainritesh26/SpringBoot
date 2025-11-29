package com.springlearning.product.controller;

import com.springlearning.product.dto.CategoryDTO;
import com.springlearning.product.exception.CategoryAlreadyExistsException;
import com.springlearning.product.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    //get all categories
    @GetMapping
    public List<CategoryDTO> getAllCategory(){
        return categoryService.getAllCategory();
    }

    //create categories
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
//        try {
//            CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
//        } catch (CategoryAlreadyExistsException exception){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
//        }
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    //get category by id
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //delete category
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
