package com.example.carrental.services;

import com.example.carrental.model.Bill;
import com.example.carrental.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements IBillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }
}
