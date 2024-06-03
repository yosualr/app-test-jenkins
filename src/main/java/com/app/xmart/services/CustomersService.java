package com.app.xmart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.xmart.dto.UserLoginRequest;
import com.app.xmart.dto.UserLoginResponse;
import com.app.xmart.model.Customers;
import com.app.xmart.repositories.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomersService {

    CustomerRepository customerRepository;
    
    public List<Customers> findAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customers> findByIdCustomers(Integer customerId) {
        return customerRepository.findById(customerId)
                .or(() -> {
                    throw new EntityNotFoundException("Customer not found");
                });
    }


    public UserLoginResponse login(UserLoginRequest userLoginRequest){
        try {
            Customers userExist = customerRepository.findById(userLoginRequest.getCustomerId())
            .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
            if (!userExist.getCustomerName().equals(userLoginRequest.getCustomerName())) {
                throw new IllegalArgumentException("User Not Found");
            }
            UserLoginResponse userLoginResponse = new UserLoginResponse();
            userLoginResponse.setCustomerId(userExist.getCustomerId());
            userLoginResponse.setCustomerName(userExist.getCustomerName());
            userLoginResponse.setCustomerWallet(userExist.getCustomerWallet());
            
            return userLoginResponse;

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to login: " + e.getMessage(), e);
        }
    }
}
