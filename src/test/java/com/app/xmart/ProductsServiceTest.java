package com.app.xmart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.app.xmart.dto.AddProductRequest;
import com.app.xmart.dto.AddProductResponse;
import com.app.xmart.model.Products;
import com.app.xmart.repositories.ProductsRepository;
import com.app.xmart.services.ProductsService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = {ProductsServiceTest.class})
@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {

    @Mock
    private ProductsRepository productsRepository;

    @InjectMocks
    private ProductsService productsService;

    private Products product;

    @BeforeEach
    void setup() {
        product = new Products();
        product.setRfid(1);
        product.setRfid(12345);
        product.setProductName("Sample Product");
        product.setProductPrice(100);
    }

    @Test
    void testFindAllProducts() {
        when(productsRepository.findAll()).thenReturn(Arrays.asList(product));

        List<Products> products = productsService.findAllProducts();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

    @Test
    void testFindByIdProducts_ProductExists() {
        when(productsRepository.findById(1)).thenReturn(Optional.of(product));

        Optional<Products> foundProduct = productsService.findByIdProducts(1);

        assertTrue(foundProduct.isPresent());
        assertEquals(product, foundProduct.get());
    }

    @Test
    void testFindByIdProducts_ProductNotFound() {
        when(productsRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            productsService.findByIdProducts(1);
        });
    }

    @Test
    void testAddProduct_Success() {
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setRfid(12345);
        addProductRequest.setProductName("Sample Product");
        addProductRequest.setProductPrice(100);

        when(productsRepository.save(any(Products.class))).thenReturn(product);

        AddProductResponse addProductResponse = productsService.addProduct(addProductRequest);

        assertNotNull(addProductResponse);
        assertEquals(product.getRfid(), addProductResponse.getRfid());
        assertEquals(product.getProductName(), addProductResponse.getProductName());
        assertEquals(product.getProductPrice(), addProductResponse.getProductPrice());
    }

    @Test
    void testAddProduct_Failure() {
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setRfid(12345);
        addProductRequest.setProductName("Sample Product");
        addProductRequest.setProductPrice(100);

        when(productsRepository.save(any(Products.class))).thenThrow(new RuntimeException("Database Error"));

        assertThrows(EntityNotFoundException.class, () -> {
            productsService.addProduct(addProductRequest);
        });
    }
    @Test
    void testAddProductRequest_Constructor() {
        AddProductRequest addProductRequest = new AddProductRequest(12345, "Sample Product", 100);

        assertNotNull(addProductRequest);
        assertEquals(12345, addProductRequest.getRfid());
        assertEquals("Sample Product", addProductRequest.getProductName());
        assertEquals(100, addProductRequest.getProductPrice());
    }

    @Test
    void testAddProductRequest_GettersAndSetters() {
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setRfid(12345);
        addProductRequest.setProductName("Sample Product");
        addProductRequest.setProductPrice(100);

        assertEquals(12345, addProductRequest.getRfid());
        assertEquals("Sample Product", addProductRequest.getProductName());
        assertEquals(100, addProductRequest.getProductPrice());
    }

    @Test
    void testToString() {
        AddProductRequest addProductRequest = new AddProductRequest(12345, "Sample Product", 100);
        String expectedToString = "AddProductRequest(rfid=12345, productName=Sample Product, productPrice=100)";
        assertEquals(expectedToString, addProductRequest.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        AddProductRequest addProductRequest1 = new AddProductRequest(12345, "Sample Product", 100);
        AddProductRequest addProductRequest2 = new AddProductRequest(12345, "Sample Product", 100);

        assertEquals(addProductRequest1, addProductRequest2);
        assertEquals(addProductRequest1.hashCode(), addProductRequest2.hashCode());
    }

}
