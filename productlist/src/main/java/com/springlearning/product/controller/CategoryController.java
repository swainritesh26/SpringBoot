package com.springlearning.product.controller;

import com.springlearning.product.dto.CategoryDTO;
import com.springlearning.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Category REST API CRUD operation",
        description = "CREATE, READ, UPDATE and DELETE operations for Category REST API "
)
@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    //get all categories
    @Operation(
            summary = "Fetch all Categories",
            description = "REST API to fetch all Categories along with their products"
    )
    @GetMapping
    public List<CategoryDTO> getAllCategory(){
        return categoryService.getAllCategory();
    }

    //create categories
    @Operation(
            summary = "Create Category",
            description = "REST API to create Category"
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    //get category by id
    @Operation(
            summary = "Fetch Category by categoryId",
            description = "REST API to fetch Category by categoryId"
    )
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //delete category
    @Operation(
            summary = "Delete Category by categoryId",
            description = "REST API to delete Category by categoryId"
    )
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
