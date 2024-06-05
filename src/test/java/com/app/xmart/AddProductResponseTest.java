package com.app.xmart;
import static org.junit.jupiter.api.Assertions.*;

import com.app.xmart.dto.AddProductResponse;
import org.junit.jupiter.api.Test;

class AddProductResponseTest {

    @Test
    void testNoArgsConstructor() {
        AddProductResponse addProductResponse = new AddProductResponse();
        assertNull(addProductResponse.getRfid());
        assertNull(addProductResponse.getProductName());
        assertNull(addProductResponse.getProductPrice());
    }

    @Test
    void testAllArgsConstructor() {
        Integer rfid = 12345;
        String productName = "Sample Product";
        Integer productPrice = 100;

        AddProductResponse addProductResponse = new AddProductResponse(rfid, productName, productPrice);

        assertEquals(rfid, addProductResponse.getRfid());
        assertEquals(productName, addProductResponse.getProductName());
        assertEquals(productPrice, addProductResponse.getProductPrice());
    }

    @Test
    void testGetterSetterRfid() {
        AddProductResponse addProductResponse = new AddProductResponse();
        Integer rfid = 12345;
        addProductResponse.setRfid(rfid);
        assertEquals(rfid, addProductResponse.getRfid());
    }

    @Test
    void testGetterSetterProductName() {
        AddProductResponse addProductResponse = new AddProductResponse();
        String productName = "Sample Product";
        addProductResponse.setProductName(productName);
        assertEquals(productName, addProductResponse.getProductName());
    }

    @Test
    void testGetterSetterProductPrice() {
        AddProductResponse addProductResponse = new AddProductResponse();
        Integer productPrice = 100;
        addProductResponse.setProductPrice(productPrice);
        assertEquals(productPrice, addProductResponse.getProductPrice());
    }

    @Test
    void testToString() {
        AddProductResponse addProductResponse = new AddProductResponse(12345, "Sample Product", 100);
        String expectedToString = "AddProductResponse(rfid=12345, productName=Sample Product, productPrice=100)";
        assertEquals(expectedToString, addProductResponse.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        AddProductResponse addProductResponse1 = new AddProductResponse(12345, "Sample Product", 100);
        AddProductResponse addProductResponse2 = new AddProductResponse(12345, "Sample Product", 100);

        assertEquals(addProductResponse1, addProductResponse2);
        assertEquals(addProductResponse1.hashCode(), addProductResponse2.hashCode());
    }

    @Test
    void testNotEqualsAndHashCode() {
        AddProductResponse addProductResponse1 = new AddProductResponse(12345, "Sample Product", 100);
        AddProductResponse addProductResponse2 = new AddProductResponse(54321, "Different Product", 200);

        assertNotEquals(addProductResponse1, addProductResponse2);
        assertNotEquals(addProductResponse1.hashCode(), addProductResponse2.hashCode());
    }
}

