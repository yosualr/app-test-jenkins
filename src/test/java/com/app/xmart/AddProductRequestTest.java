package com.app.xmart;
import static org.junit.jupiter.api.Assertions.*;

import com.app.xmart.dto.AddProductRequest;
import org.junit.jupiter.api.Test;

class AddProductRequestTest {

    @Test
    void testNoArgsConstructor() {
        AddProductRequest addProductRequest = new AddProductRequest();
        assertNull(addProductRequest.getRfid());
        assertNull(addProductRequest.getProductName());
        assertNull(addProductRequest.getProductPrice());
    }

    @Test
    void testAllArgsConstructor() {
        Integer rfid = 123;
        String productName = "Product";
        Integer productPrice = 100;

        AddProductRequest addProductRequest = new AddProductRequest(rfid, productName, productPrice);

        assertEquals(rfid, addProductRequest.getRfid());
        assertEquals(productName, addProductRequest.getProductName());
        assertEquals(productPrice, addProductRequest.getProductPrice());
    }

    @Test
    void testGetterSetterRfid() {
        AddProductRequest addProductRequest = new AddProductRequest();
        Integer rfid = 123;
        addProductRequest.setRfid(rfid);
        assertEquals(rfid, addProductRequest.getRfid());
    }

    @Test
    void testGetterSetterProductName() {
        AddProductRequest addProductRequest = new AddProductRequest();
        String productName = "Product";
        addProductRequest.setProductName(productName);
        assertEquals(productName, addProductRequest.getProductName());
    }

    @Test
    void testGetterSetterProductPrice() {
        AddProductRequest addProductRequest = new AddProductRequest();
        Integer productPrice = 100;
        addProductRequest.setProductPrice(productPrice);
        assertEquals(productPrice, addProductRequest.getProductPrice());
    }

    @Test
    void testToString() {
        AddProductRequest addProductRequest = new AddProductRequest(123, "Product", 100);
        String expectedToString = "AddProductRequest(rfid=123, productName=Product, productPrice=100)";
        assertEquals(expectedToString, addProductRequest.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        AddProductRequest addProductRequest1 = new AddProductRequest(123, "Product", 100);
        AddProductRequest addProductRequest2 = new AddProductRequest(123, "Product", 100);

        assertEquals(addProductRequest1, addProductRequest2);
        assertEquals(addProductRequest1.hashCode(), addProductRequest2.hashCode());
    }

    @Test
    void testConstructorAndGetters() {
        // Given
        Integer rfid = 12345;
        String productName = "Sample Product";
        Integer productPrice = 100;

        // When
        AddProductRequest addProductRequest = new AddProductRequest(rfid, productName, productPrice);

        // Then
        assertEquals(rfid, addProductRequest.getRfid());
        assertEquals(productName, addProductRequest.getProductName());
        assertEquals(productPrice, addProductRequest.getProductPrice());
    }

    @Test
    void testSetters() {
        // Given
        Integer rfid = 12345;
        String productName = "Sample Product";
        Integer productPrice = 100;
        AddProductRequest addProductRequest = new AddProductRequest();

        // When
        addProductRequest.setRfid(rfid);
        addProductRequest.setProductName(productName);
        addProductRequest.setProductPrice(productPrice);

        // Then
        assertEquals(rfid, addProductRequest.getRfid());
        assertEquals(productName, addProductRequest.getProductName());
        assertEquals(productPrice, addProductRequest.getProductPrice());
    }
}
