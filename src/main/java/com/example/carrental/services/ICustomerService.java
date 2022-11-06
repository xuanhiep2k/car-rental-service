package com.example.carrental.services;

import com.example.carrental.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> findByFullName(String name);

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    List<Customer> getAllCustomers();

    void delete(Long id);
}
