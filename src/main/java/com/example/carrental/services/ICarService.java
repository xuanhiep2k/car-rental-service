package com.example.carrental.services;

import com.example.carrental.model.Car;
import com.example.carrental.model.PageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ICarService {

    List<Car> findAll(Specification<Car> specification);

    Page<Car> findAll(PageModel pageModel);

    Page<Car> findByName(String name,PageModel pageModel);

    Car save(Car car);

    Optional<Car> findById(Long id);

    void delete(Long id);
}
