package com.example.carrental.service;

import com.example.carrental.model.Car;
import com.example.carrental.model.Contract;
import com.example.carrental.model.Customer;
import com.example.carrental.model.Rental;
import com.example.carrental.repositories.CarRepository;
import com.example.carrental.services.CarServiceImpl;
import com.example.carrental.specs.SpecificationFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl carService;

    @Mock
    SpecificationFilter specificationFilter;

    @Test
    void searchCar_fullEnter() {
        List mockCars = new ArrayList();

        Contract contract = new Contract(1L, 1000, new Date(), new Date(), new Date(), "", "", new ArrayList<>(), null, null);
        Car car = new Car(1L, "Hyundai", "18A-12345", "Accent", "Xe loai A", "", new ArrayList<>());
        Car car1 = new Car(2L, "Merce", "17A-12345", "GLC", "Xe loai B", "", new ArrayList<>());

        mockCars.add(car1);
        mockCars.add(car);

        Specification<Car> specification = specificationFilter.getSpecsSearchCar(car.getName(), contract.getStartRent(), contract.getEndRent());

        when(carRepository.findAll(specification)).thenReturn(mockCars);

        List<Car> actualCars = carService.findAll(specification);

        assertThat(actualCars.size()).isEqualTo(mockCars.size());

        verify(carRepository).findAll(specification);
    }

    @Test
    void searchCar_noEnter() {
        List mockCars = new ArrayList();

        Contract contract = new Contract(1L, 1000, new Date(), new Date(), new Date(), "", "", new ArrayList<>(), null, null);
        Car car = new Car(1L, "Hyundai", "18A-12345", "Accent", "Xe loai A", "", new ArrayList<>());
        Car car1 = new Car(2L, "Merce", "17A-12345", "GLC", "Xe loai B", "", new ArrayList<>());

        mockCars.add(car1);
        mockCars.add(car);

        Specification<Car> specification = specificationFilter.getSpecsSearchCar("", contract.getStartRent(), contract.getEndRent());

        when(carRepository.findAll(specification)).thenReturn(mockCars);

        assertThrows(InputMismatchException.class, () -> {
            carService.findAll(specification);
        });

        verify(carRepository).findAll(specification);
    }

    @Test
    void filterCar_fullEnter() {
        List mockCars = new ArrayList();

        Contract contract = new Contract(1L, 1000, new Date(), new Date(), new Date(), "", "", new ArrayList<>(), null, null);
        Car car = new Car(1L, "Hyundai", "18A-12345", "Accent", "Xe loai A", "", new ArrayList<>());
        Car car1 = new Car(2L, "Merce", "17A-12345", "GLC", "Xe loai B", "", new ArrayList<>());

        mockCars.add(car1);
        mockCars.add(car);

        Specification<Car> specification = specificationFilter.getSpecsFilterCar("", "", "", "", contract.getStartRent(), contract.getEndRent());

        when(carRepository.findAll(specification)).thenReturn(mockCars);

        List<Car> actualCars = carService.findAll(specification);

        assertThat(actualCars.size()).isEqualTo(mockCars.size());

        verify(carRepository).findAll(specification);
    }

}
