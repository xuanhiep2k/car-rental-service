package com.example.carrental.services;

import com.example.carrental.model.Bill;
import com.example.carrental.model.Car;
import com.example.carrental.model.Contract;
import com.example.carrental.model.Rental;
import com.example.carrental.repositories.BillRepository;
import com.example.carrental.repositories.CarRepository;
import com.example.carrental.repositories.ContractRepository;
import com.example.carrental.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements IContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public Contract save(Contract contract) {
        Contract newContract = new Contract(null, contract.getPrice(), contract.getStartRent(), contract.getEndRent(), contract.getDateCreate(), contract.getCollateral(), contract.getNote(), new ArrayList<>(), contract.getCustomer(), null);
        contractRepository.save(newContract);
        newContract.setRentals(contract.getRentals()
                .stream().map(rental -> {
                    Optional<Car> car = carRepository.findById(rental.getCar().getId());
                    Rental newRental = new Rental();
                    newRental.setCar(car.get());
                    newRental.setContract(newContract);
                    rentalRepository.save(newRental);
                    return newRental;
                }).collect(Collectors.toList()));
        Bill bill = new Bill(null, contract.getBill().getPaymentType(), contract.getBill().getPaymentDate(), contract.getBill().getNote(), newContract, contract.getBill().getUser());
        billRepository.save(bill);
        return newContract;
    }

    @Override
    public List<Contract> findAll(Specification<Contract> specification) {
        return contractRepository.findAll(specification);
    }
}
