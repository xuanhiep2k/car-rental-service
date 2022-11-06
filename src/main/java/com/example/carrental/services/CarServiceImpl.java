package com.example.carrental.services;

import com.example.carrental.model.Car;
import com.example.carrental.model.PageModel;
import com.example.carrental.repositories.CarRepository;
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
public class CarServiceImpl implements ICarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll(Specification<Car> specification) {
        return carRepository.findAll(specification);
    }

    @Override
    public Page<Car> findAll(PageModel pageModel) {
        Sort sort = Sort.by(pageModel.getSortDirection(), pageModel.getSortBy());
        Pageable pageable = PageRequest.of(pageModel.getPageNumber(), pageModel.getPageSize(), sort);
        return carRepository.findAll(pageable);
    }

    @Override
    public List<Car> findByName(String name) {
        return carRepository.findByName(name);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

}
