package com.app.xmart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.app.xmart.dto.UserLoginRequest;
import com.app.xmart.dto.UserLoginResponse;
import com.app.xmart.model.Customers;
import com.app.xmart.repositories.CustomerRepository;
import com.app.xmart.services.CustomersService;
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

@SpringBootTest(classes = {CustomerServiceTest.class})
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomersService customersService;

    private Customers customer;

    @BeforeEach
    void setup() {
        customer = new Customers();
        customer.setCustomerId(1);
        customer.setCustomerName("John Doe");
        customer.setCustomerWallet(100);
    }

    @Test
    void testFindAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));

        List<Customers> customers = customersService.findAllCustomers();

        assertNotNull(customers);
        assertEquals(1, customers.size());
        assertEquals(customer, customers.get(0));
    }

    @Test
    void testFindByIdCustomers_CustomerExists() {
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Optional<Customers> foundCustomer = customersService.findByIdCustomers(1);

        assertTrue(foundCustomer.isPresent());
        assertEquals(customer, foundCustomer.get());
    }

    @Test
    void testFindByIdCustomers_CustomerNotFound() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            customersService.findByIdCustomers(1);
        });
    }

    @Test
    void testLogin_UserExists() {
        UserLoginRequest loginRequest = new UserLoginRequest(1, "John Doe");

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        UserLoginResponse loginResponse = customersService.login(loginRequest);

        assertNotNull(loginResponse);
        assertEquals(customer.getCustomerId(), loginResponse.getCustomerId());
        assertEquals(customer.getCustomerName(), loginResponse.getCustomerName());
        assertEquals(customer.getCustomerWallet(), loginResponse.getCustomerWallet());
    }

    @Test
    void testLogin_UserNotFound() {
        UserLoginRequest loginRequest = new UserLoginRequest(1, "John Doe");

        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            customersService.login(loginRequest);
        });
    }

    @Test
    void testLogin_InvalidUserName() {
        UserLoginRequest loginRequest = new UserLoginRequest(1, "Invalid Name");

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        assertThrows(RuntimeException.class, () -> {
            customersService.login(loginRequest);
        });
    }
}
