package com.app.xmart;

import static org.junit.jupiter.api.Assertions.*;

import com.app.xmart.dto.UserLoginResponse;
import org.junit.jupiter.api.Test;

class UserLoginResponseTest {

    @Test
    void testNoArgsConstructor() {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        assertNull(userLoginResponse.getCustomerId());
        assertNull(userLoginResponse.getCustomerName());
        assertNull(userLoginResponse.getCustomerWallet());
    }

    @Test
    void testAllArgsConstructor() {
        Integer customerId = 123;
        String customerName = "John Doe";
        Integer customerWallet = 100;

        UserLoginResponse userLoginResponse = new UserLoginResponse(customerId, customerName, customerWallet);

        assertEquals(customerId, userLoginResponse.getCustomerId());
        assertEquals(customerName, userLoginResponse.getCustomerName());
        assertEquals(customerWallet, userLoginResponse.getCustomerWallet());
    }

    @Test
    void testGetterSetterCustomerId() {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        Integer customerId = 123;
        userLoginResponse.setCustomerId(customerId);
        assertEquals(customerId, userLoginResponse.getCustomerId());
    }

    @Test
    void testGetterSetterCustomerName() {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        String customerName = "John Doe";
        userLoginResponse.setCustomerName(customerName);
        assertEquals(customerName, userLoginResponse.getCustomerName());
    }

    @Test
    void testGetterSetterCustomerWallet() {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        Integer customerWallet = 100;
        userLoginResponse.setCustomerWallet(customerWallet);
        assertEquals(customerWallet, userLoginResponse.getCustomerWallet());
    }

    @Test
    void testToString() {
        UserLoginResponse userLoginResponse = new UserLoginResponse(123, "John Doe", 100);
        String expectedToString = "UserLoginResponse(customerId=123, customerName=John Doe, customerWallet=100)";
        assertEquals(expectedToString, userLoginResponse.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        UserLoginResponse userLoginResponse1 = new UserLoginResponse(123, "John Doe", 100);
        UserLoginResponse userLoginResponse2 = new UserLoginResponse(123, "John Doe", 100);

        assertEquals(userLoginResponse1, userLoginResponse2);
        assertEquals(userLoginResponse1.hashCode(), userLoginResponse2.hashCode());
    }
}

