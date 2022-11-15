package com.example.carrental.services;

import com.example.carrental.model.PageModel;
import com.example.carrental.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Page<Customer> findByFullName(String name,PageModel pageModel);

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    Page<Customer> findAll(PageModel pageModel);

    void delete(Long id);
}
