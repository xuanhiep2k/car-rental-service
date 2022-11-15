package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.model.Contract;
import com.example.carrental.model.Rental;
import com.example.carrental.repositories.ContractRepository;
import com.example.carrental.repositories.RentalRepository;
import com.example.carrental.services.ContractServiceImpl;
import com.example.carrental.services.RentalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContractServiceTest {

    @Mock
    ContractRepository contractRepository;

    @InjectMocks
    ContractServiceImpl contractService;

    @Mock
    RentalRepository rentalRepository;

    @InjectMocks
    RentalServiceImpl rentalService;

    @Test
    void saveContract_greater1Car() {
        List<Rental> mockRentals = new ArrayList<>();
        Car car = new Car(1L, "Hyundai", "18A-12345", "Accent", "Xe loai A", "", new ArrayList<>());
        Car car1 = new Car(2L, "Merce", "17A-12345", "GLC", "Xe loai B", "", new ArrayList<>());
        Contract contract = new Contract(1L, 1000, new Date(), new Date(), new Date(), "", "", new ArrayList<>(), null, null);

        Rental rental = new Rental(1L, car, contract);
        Rental rental1 = new Rental(1L, car1, contract);

        when(contractRepository.save(contract)).thenReturn(contract);
        Mockito.lenient().when(rentalRepository.save(rental)).thenReturn(rental);
        Mockito.lenient().when(rentalRepository.save(rental1)).thenReturn(rental1);

        Contract actualContract = contractService.save(contract);
        Rental actualRental = rentalService.save(rental);
        Rental actualRental1 = rentalService.save(rental1);

        assertThat(actualContract).isEqualTo(contract);
        assertThat(actualRental).isEqualTo(actualRental);
        assertThat(actualRental).isEqualTo(actualRental1);

        verify(contractRepository).save(contract);
        verify(rentalRepository).save(rental);
        verify(rentalRepository).save(rental1);
    }

    @Test
    void saveContract_noCar() {
        Contract contract = new Contract(1L, 1000, new Date(), new Date(), new Date(), "Dong ho", "", new ArrayList<>(), null, null);

        Rental rental = new Rental(1L, null, contract);
        Rental rental1 = new Rental(1L, null, contract);

        when(contractRepository.save(contract)).thenReturn(contract);
        Mockito.lenient().when(rentalRepository.save(rental)).thenReturn(new Rental());
        Mockito.lenient().when(rentalRepository.save(rental1)).thenReturn(new Rental());

        Contract actualContract = contractService.save(contract);
        Rental actualRental = rentalService.save(rental);
        Rental actualRental1 = rentalService.save(rental1);

        assertThat(actualContract).isEqualTo(contract).isNull();
        assertThat(actualRental).isEqualTo(actualRental).isNull();
        assertThat(actualRental).isEqualTo(actualRental1);

        verify(contractRepository).save(contract);
        verify(rentalRepository).save(rental);
        verify(rentalRepository).save(rental1);
    }
}
