package com.app.xmart;

import com.app.xmart.controllers.CustomerControllers;
import com.app.xmart.dto.UserLoginRequest;
import com.app.xmart.dto.UserLoginResponse;
import com.app.xmart.model.Customers;
import com.app.xmart.services.CustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CustomerControllersTest {

    @Mock
    private CustomersService customersService;

    @InjectMocks
    private CustomerControllers customerControllers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        // Given
        List<Customers> customersList = Arrays.asList(new Customers(), new Customers());

        // When
        when(customersService.findAllCustomers()).thenReturn(customersList);
        ResponseEntity<List<Customers>> responseEntity = customerControllers.getAllCustomers();

        // Then
        assertEquals(customersList, responseEntity.getBody());
    }

    @Test
    void testGetDataDetailCustomers() {
        // Given
        Integer customerId = 1;
        Optional<Customers> customerOptional = Optional.of(new Customers());

        // When
        when(customersService.findByIdCustomers(customerId)).thenReturn(customerOptional);
        ResponseEntity<Optional<Customers>> responseEntity = customerControllers.getDataDetailCustomers(customerId);

        // Then
        assertEquals(customerOptional, responseEntity.getBody());
    }

    @Test
    void testLoginCustomer() {
        // Given
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        UserLoginResponse userLoginResponse = new UserLoginResponse();

        // When
        when(customersService.login(userLoginRequest)).thenReturn(userLoginResponse);
        ResponseEntity<UserLoginResponse> responseEntity = customerControllers.loginCustomer(userLoginRequest);

        // Then
        assertEquals(userLoginResponse, responseEntity.getBody());
    }
}
