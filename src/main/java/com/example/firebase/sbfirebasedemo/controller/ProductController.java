package com.example.firebase.sbfirebasedemo.controller;

import com.example.firebase.sbfirebasedemo.entity.Product;
import com.example.firebase.sbfirebasedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productservice;

    @PostMapping("/products")
    public String save(@RequestBody Product pProduct) throws ExecutionException, InterruptedException {
        return productservice.saveProduct(pProduct);
    }

    @GetMapping("/products/{name}")
    public Product getProducts(@PathVariable String name) throws ExecutionException, InterruptedException {
        return productservice.getProductDetails(name);
    }

    @PutMapping("/products")
    public String update(@RequestBody Product pProduct) throws ExecutionException, InterruptedException {
        return productservice.updateProduct(pProduct);
    }

    @DeleteMapping("/products/{name}")
    public String delete(@PathVariable String name) throws ExecutionException, InterruptedException {
        return productservice.deleteProduct(name);
    }

}
