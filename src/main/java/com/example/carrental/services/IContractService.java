package com.example.carrental.services;

import com.example.carrental.model.Contract;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IContractService {
    Contract save(Contract contract);

    List<Contract> findAll(Specification<Contract> specification);
}
