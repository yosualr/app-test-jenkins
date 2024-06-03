package com.app.xmart.services;

import java.util.List;
import java.util.Optional;

import com.app.xmart.dto.AddProductRequest;
import com.app.xmart.dto.AddProductResponse;
import org.springframework.stereotype.Service;

import com.app.xmart.model.Products;
import com.app.xmart.repositories.ProductsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductsService {
    
    ProductsRepository productsRepository;

    public List<Products> findAllProducts(){
        return productsRepository.findAll();
    }

    public Optional<Products> findByIdProducts(Integer productId){
        return productsRepository.findById(productId)
                .or(() -> {
                    throw new EntityNotFoundException("Product not found");
                });
    }

    public AddProductResponse addProduct(AddProductRequest addProductRequest){
        try{
            Products dataProduct = new Products();
            dataProduct.setRfid(addProductRequest.getRfid());
            dataProduct.setProductName(addProductRequest.getProductName());
            dataProduct.setProductPrice(addProductRequest.getProductPrice());
            Products savedDataProduct = productsRepository.save(dataProduct);

            AddProductResponse addProductResponse = new AddProductResponse();
            addProductResponse.setRfid(savedDataProduct.getRfid());
            addProductResponse.setProductName(savedDataProduct.getProductName());
            addProductResponse.setProductPrice(savedDataProduct.getProductPrice());

            return addProductResponse;
        } catch (Exception e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

}
