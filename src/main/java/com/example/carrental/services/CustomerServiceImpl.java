package com.example.carrental.services;

import com.example.carrental.model.Customer;
import com.example.carrental.model.PageModel;
import com.example.carrental.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Specification<Customer> specification, PageModel pageModel) {
        Sort sort = Sort.by(pageModel.getSortDirection(), pageModel.getSortBy());
        Pageable pageable = PageRequest.of(pageModel.getPageNumber(), pageModel.getPageSize(), sort);
        return customerRepository.findAll(specification, pageable);
    }
    @Override
    public Page<Customer> findByName(String name, PageModel pageModel) {
        Sort sort = Sort.by(pageModel.getSortDirection(), pageModel.getSortBy());
        Pageable pageable = PageRequest.of(pageModel.getPageNumber(), pageModel.getPageSize(), sort);
        return customerRepository.findByFullName(name, pageable);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Page<Customer> findAll(PageModel pageModel) {
        Sort sort = Sort.by(pageModel.getSortDirection(), pageModel.getSortBy());
        Pageable pageable = PageRequest.of(pageModel.getPageNumber(), pageModel.getPageSize(), sort);
        return customerRepository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }


}
