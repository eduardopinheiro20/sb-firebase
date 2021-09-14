package com.example.firebase.sbfirebasedemo.controller;

import com.example.firebase.sbfirebasedemo.entity.Product;
import com.example.firebase.sbfirebasedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/products")
    public List<Product> getAllProducts() throws ExecutionException, InterruptedException {
        return productservice.getProducts();
    }

    @GetMapping("/products/{name}")
    public Product getProductsByName(@PathVariable String name) throws ExecutionException, InterruptedException {
        return productservice.getProductByName(name);
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
