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
}
