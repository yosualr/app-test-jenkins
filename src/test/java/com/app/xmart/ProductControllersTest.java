package com.app.xmart;
import com.app.xmart.controllers.ProductControllers;
import com.app.xmart.dto.AddProductRequest;
import com.app.xmart.dto.AddProductResponse;
import com.app.xmart.model.Products;
import com.app.xmart.services.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllersTest {

    @Mock
    private ProductsService productsService;

    @InjectMocks
    private ProductControllers productControllers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Given
        List<Products> productsList = Arrays.asList(new Products(), new Products());
        when(productsService.findAllProducts()).thenReturn(productsList);

        // When
        ResponseEntity<List<Products>> responseEntity = productControllers.getAllProducts();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productsList, responseEntity.getBody());
    }

    @Test
    void testGetDataDetailCustomers() {
        // Given
        Integer productId = 1;
        Optional<Products> product = Optional.of(new Products());
        when(productsService.findByIdProducts(productId)).thenReturn(product);

        // When
        ResponseEntity<Optional<Products>> responseEntity = productControllers.getDataDetailCustomers(productId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
    }

    @Test
    void testAddProduct() {
        // Given
        AddProductRequest addProductRequest = new AddProductRequest();
        AddProductResponse addProductResponse = new AddProductResponse();
        when(productsService.addProduct(addProductRequest)).thenReturn(addProductResponse);

        // When
        ResponseEntity<AddProductResponse> responseEntity = productControllers.addProduct(addProductRequest);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(addProductResponse, responseEntity.getBody());
    }
}

