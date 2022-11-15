package com.example.carrental.controller;

import com.example.carrental.model.Customer;
import com.example.carrental.model.PageModel;
import com.example.carrental.model.ResponseObject;
import com.example.carrental.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/search")
    ResponseEntity<ResponseObject> searchByName(@RequestParam(value = "name") String name, PageModel pageModel) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Get data successfully", iCustomerService.findByFullName(name, pageModel))
        );
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Add customer successfully", iCustomerService.save(customer))
        );
    }

    @GetMapping("/getAllCustomers")
    ResponseEntity<ResponseObject> getAllCustomers(PageModel pageModel) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Get data successfully", iCustomerService.findAll(pageModel))
        );
    }

    @PutMapping("/updateCustomer/{id}")
    ResponseEntity<ResponseObject> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> updateCustomer = iCustomerService.findById(id);
        updateCustomer.get().setName(customer.getName());
        updateCustomer.get().setAddress(customer.getAddress());
        updateCustomer.get().setTel(customer.getTel());
        updateCustomer.get().setNote(customer.getNote());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Get data successfully", iCustomerService.save(updateCustomer.get()))
        );
    }

    @DeleteMapping("/deleteCustomer/{id}")
    ResponseEntity<ResponseObject> deleteCustomer(@PathVariable Long id) {
        iCustomerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete successfully", "")
        );
    }
}
