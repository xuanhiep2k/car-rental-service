package com.example.carrental.controller;

import com.example.carrental.model.*;
import com.example.carrental.services.ICarService;
import com.example.carrental.specs.SpecificationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private ICarService iCarService;

    @Autowired
    private SpecificationFilter specificationFilter;


    @GetMapping("/getAllCars")
    ResponseEntity<ResponseObject> getAllCars(PageModel pageModel) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Get successfully", iCarService.findAll(pageModel))
        );
    }

    @GetMapping("/searchCarByName")
    ResponseEntity<ResponseObject> searchCarByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Get successfully", iCarService.findByName(name))
        );
    }

    @GetMapping("/search")
    ResponseEntity<ResponseObject> searchCar(@RequestParam(value = "name") String name,
                                             @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                             @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Specification<Car> specification = specificationFilter.getSpecsSearchCar(name, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", iCarService.findAll(specification))
        );
    }


    @GetMapping("/filter")
    ResponseEntity<ResponseObject> filterCar(@RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "licensePlate", required = false) String licensePlate,
                                             @RequestParam(value = "model", required = false) String model,
                                             @RequestParam(value = "type", required = false) String type,
                                             @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                             @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        Specification<Car> specification = specificationFilter.getSpecsFilterCar(name, licensePlate, model, type, startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Successfully", iCarService.findAll(specification))
        );
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> saveCar(@RequestBody Car car) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Add successfully", iCarService.save(car))
        );
    }

    @PutMapping("/updateCar/{id}")
    ResponseEntity<ResponseObject> updateCar(@PathVariable Long id, @RequestBody Car car) {
        Optional<Car> updateCar = iCarService.findById(id);
        updateCar.get().setName(car.getName());
        updateCar.get().setLicensePlate(car.getLicensePlate());
        updateCar.get().setModel(car.getModel());
        updateCar.get().setType(car.getType());
        updateCar.get().setDescription(car.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update data successfully", iCarService.save(updateCar.get()))
        );
    }

    @DeleteMapping("/deleteCar/{id}")
    ResponseEntity<ResponseObject> deleteCar(@PathVariable Long id) {
        iCarService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Delete successfully", "")
        );
    }
}
