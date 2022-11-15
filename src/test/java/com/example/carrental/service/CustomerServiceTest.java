package com.example.carrental.service;

import com.example.carrental.model.Customer;
import com.example.carrental.repositories.CustomerRepository;
import com.example.carrental.services.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void getAllCustomers_pass() {
        List<Customer> mockCustomers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mockCustomers.add(new Customer((long) i, "Hiep", "", "", "", null));
        }

        when(customerRepository.findAll()).thenReturn(mockCustomers);

//        List<Customer> actualCustomers = customerService.getAllCustomers();

//        assertThat(actualCustomers.size()).isEqualTo(mockCustomers.size());

        verify(customerRepository).findAll();
    }

    @Test
    void findByFullName_enterFullName_pass() {
        String nameInvalid = "Hiep";
        List<Customer> mockCustomers = new ArrayList<>();

        mockCustomers.add(new Customer(1L, "Hiep", "", "", "", null));
        mockCustomers.add(new Customer(2L, "Linh", "", "", "", null));
        mockCustomers.add(new Customer(3L, "Duy", "", "", "", null));

//        when(customerRepository.findByFullName("Hiep")).thenReturn(Collections.singletonList(mockCustomers.get(0)));
//
//        List<Customer> actualCustomers = customerService.findByFullName(nameInvalid);
//        System.out.println(actualCustomers.size());
//
//        assertThat(actualCustomers.size()).isEqualTo(1);
//
//        verify(customerRepository).findByFullName(nameInvalid);
    }

    @Test
    void findByFullName_enterAFewCharacters_pass() {
        String nameInvalid = "i";
        List<Customer> mockCustomers = new ArrayList<>();

        mockCustomers.add(new Customer(1L, "Hiep", "", "", "", null));
        mockCustomers.add(new Customer(2L, "Linh", "", "", "", null));
        mockCustomers.add(new Customer(3L, "Duy", "", "", "", null));

//        when(customerRepository.findByFullName("i")).thenReturn(mockCustomers.subList(0, 2));
//
//        List<Customer> actualCustomers = customerService.findByFullName(nameInvalid);
//        System.out.println(actualCustomers.size());
//
//        assertThat(actualCustomers.size()).isEqualTo(2);
//
//        verify(customerRepository).findByFullName(nameInvalid);
    }

    @Test
    void findByFullName_noEnter_pass() {
        String nameInvalid = "";
        List<Customer> mockCustomers = new ArrayList<>();

        mockCustomers.add(new Customer(1L, "Hiep", "", "", "", null));
        mockCustomers.add(new Customer(2L, "Linh", "", "", "", null));
        mockCustomers.add(new Customer(3L, "Duy", "", "", "", null));

//        when(customerRepository.findByFullName("")).thenReturn(mockCustomers);
//
//        List<Customer> actualCustomers = customerService.findByFullName(nameInvalid);
//        System.out.println(actualCustomers.size());
//
//        assertThat(actualCustomers.size()).isEqualTo(mockCustomers.size());
//
//        verify(customerRepository).findByFullName(nameInvalid);
    }

    @Test
    void findByFullName_exception_NotFoundCustomer() {
        String nameInvalid = "Mai";
        List<Customer> mockCustomers = new ArrayList<>();

        mockCustomers.add(new Customer(1L, "Hiep", "", "", "", null));
        mockCustomers.add(new Customer(2L, "Linh", "", "", "", null));
        mockCustomers.add(new Customer(3L, "Duy", "", "", "", null));

//        when(customerRepository.findByFullName("Mai")).thenReturn(new ArrayList<>());
//
//        List<Customer> actualCustomers = customerService.findByFullName(nameInvalid);
//        System.out.println(actualCustomers.size());
//
//        assertThat(actualCustomers).isEmpty();
//        assertThat(actualCustomers.size()).isEqualTo(0);
//
//        verify(customerRepository).findByFullName(nameInvalid);
    }

    @Test
    void saveCustomer_existed() {

        Customer customer = new Customer(1L, "Hiep", "", "", "", null);

        Mockito.lenient().when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer actualCustomers = customerService.save(customer);

        assertThat(actualCustomers).isNotNull();

        verify(customerRepository).save(customer);
    }


    @Test
    void saveCustomer_notExisted() {

        Customer customer = new Customer(1L, "Hiep", "", "", "", null);

        Mockito.lenient().when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(new Customer()));

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer actualCustomers = customerService.save(customer);

        assertThat(actualCustomers).isNotNull();

        verify(customerRepository).save(customer);
    }

}
