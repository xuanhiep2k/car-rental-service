package com.example.carrental.repositories;

import com.example.carrental.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %?1%")
    Page<Customer> findByFullName(String name, Pageable pageable);

    Page<Customer> findAll(Specification<Customer> specification, Pageable pageable);
}
