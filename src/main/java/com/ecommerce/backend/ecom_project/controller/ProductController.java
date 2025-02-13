package com.ecommerce.backend.ecom_project.controller;

import com.ecommerce.backend.ecom_project.model.Product;
import com.ecommerce.backend.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
   private ProductService service;

    // Fetching all the products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>( service.getAllProducts(), HttpStatus.OK);
    }

    // Fetching ProductsById
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id)
    {

        Product product= service.getById(id);
        if(product != null)
        {
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Adding Product and image in databse.
    @PostMapping("/product" )
    public ResponseEntity<?> addProduct(@RequestBody Product product, @RequestPart MultipartFile imageFile)
    {
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // fetching the image.
    @GetMapping("/product/{prodId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int prodId)
    {
        Product product1= service.getById(prodId);
        byte[]image = product1.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product1.getImageType())).body(image);
    }

    // Updating the product

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product, @RequestPart MultipartFile imageFile)  {

        Product product1 = null;
        try {
            product1 = service.updateProduct(id,product,imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(product1 != null)
       {
           return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>("Fail to update",HttpStatus.BAD_REQUEST);
       }
    }

    // Delete Product
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    {
        Product product1 =service.getById(id);
        if(product1 != null)
        {
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Product Not found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestPart String keyword)
    {
        List<Product> products = service.searchProducts(keyword);

        if(products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
