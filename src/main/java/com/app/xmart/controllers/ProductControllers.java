package com.app.xmart.controllers;

import java.util.List;
import java.util.Optional;

import com.app.xmart.dto.AddProductRequest;
import com.app.xmart.dto.AddProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.xmart.model.Products;
import com.app.xmart.services.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductControllers {

    ProductsService productsService;

    @GetMapping("/list")
    public ResponseEntity<List<Products>> getAllProducts() {
        return ResponseEntity.ok(productsService.findAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Optional<Products>> getDataDetailCustomers(@PathVariable Integer productId) {
        return ResponseEntity.ok(productsService.findByIdProducts(productId));
    }

    @PostMapping
    public ResponseEntity<AddProductResponse> addProduct(@RequestBody AddProductRequest addProductRequest){
        AddProductResponse addProductResponse = productsService.addProduct(addProductRequest);
        return new ResponseEntity<>(addProductResponse, HttpStatus.CREATED);
    }

    
}
