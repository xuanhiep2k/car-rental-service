package com.example.carrental.services;

import com.example.carrental.model.Rental;
import com.example.carrental.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceImpl implements IRentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }
}
