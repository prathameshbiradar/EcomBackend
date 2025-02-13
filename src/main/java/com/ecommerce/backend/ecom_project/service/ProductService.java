package com.ecommerce.backend.ecom_project.service;

import com.ecommerce.backend.ecom_project.model.Product;
import com.ecommerce.backend.ecom_project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getById(int id) {
        return repository.findById(id).orElse(null);

    }


    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repository.save(product);
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());
        return repository.save(product);

    }

    public void deleteProduct(int id) {
        repository.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return repository.searchProducts(keyword);
    }
}
