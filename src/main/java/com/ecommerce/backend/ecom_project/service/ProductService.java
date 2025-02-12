package com.ecommerce.backend.ecom_project.service;

import com.ecommerce.backend.ecom_project.model.Product;
import com.ecommerce.backend.ecom_project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo repository;

    public List<Product> getAllProducts()
    {
        return repository.findAll();
    }
    public Product getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }


    public void addProduct(Product product) {
        repository.save(product);
    }
}
