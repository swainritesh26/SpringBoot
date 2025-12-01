package com.springlearning.product.controller;

import com.springlearning.product.dto.ProductDTO;
import com.springlearning.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Product REST API CRUD operation",
        description = "CREATE, READ, UPDATE and DELETE operations for Product REST API "
)
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;
    //getAllProduct
    @Operation(
            summary = "Fetch all Products",
            description = "REST API to fetch all Products"
    )
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    //get product by id
    @Operation(
            summary = "Fetch Product by productId",
            description = "REST API to fetch Product by productId"
    )
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    //createProduct
    @Operation(
            summary = "Create Product",
            description = "REST API to create Product"
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED"
    )
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO createProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);
    }

    //update Product
    @Operation(
            summary = "Update Product by productId",
            description = "REST API to update Product by productId"
    )
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }

    //delete product
    @Operation(
            summary = "Delete Product by productId",
            description = "REST API to delete Product by productId"
    )
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
