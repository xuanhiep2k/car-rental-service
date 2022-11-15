package com.example.carrental.services;

import com.example.carrental.model.PageModel;
import com.example.carrental.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface ICustomerService {
    Page<Customer> findAll(Specification<Customer> specification, PageModel pageModel);

    Page<Customer> findByName(String name, PageModel pageModel);

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    Page<Customer> findAll(PageModel pageModel);

    void delete(Long id);
}
