package com.springlearning.product.service;

import com.springlearning.product.dto.ProductDTO;
import com.springlearning.product.entity.Category;
import com.springlearning.product.entity.Product;
import com.springlearning.product.exception.CategoryNotFoundException;
import com.springlearning.product.mapper.ProductMapper;
import com.springlearning.product.repository.CategoryRepository;
import com.springlearning.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {


    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    //create Products
    public ProductDTO createProduct(ProductDTO productDTO){
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category id: "
                        + productDTO.getCategoryId() + " Not Found!"));

        //DTO to Entity
        Product product = ProductMapper.toProductEntity(productDTO,category);
        product = productRepository.save(product);
        //Entity to DTO
        return ProductMapper.toProductDTO(product);
    }

    //get All Products
    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductMapper::toProductDTO).toList();
    }

    //get Product By id
    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not Found!"));
        return ProductMapper.toProductDTO(product);
    }

    //update Product
    public ProductDTO updateProduct(Long id,ProductDTO productDTO){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not Found!"));
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category not Found!"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toProductDTO(product);
    }

    //delete Product
    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product " + id + "has been deleted! ";
    }
}
