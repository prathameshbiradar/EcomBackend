package com.ecommerce.backend.ecom_project.service;

import com.ecommerce.backend.ecom_project.model.Product;
import com.ecommerce.backend.ecom_project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


}
