package com.ecommerce.backend.ecom_project.controller;

import com.ecommerce.backend.ecom_project.model.Product;
import com.ecommerce.backend.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
   private ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

    @GetMapping("/product")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }
    @GetMapping("/product/{id}")
    public Product getById(@PathVariable int id)
    {
        return service.getById(id);
    }
    @PostMapping("/product")
    public void addProduct(@RequestBody Product product)
    {
        service.addProduct(product);
    }
}
