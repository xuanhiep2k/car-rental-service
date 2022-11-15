package com.example.carrental.repositories;

import com.example.carrental.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    //    SELECT c.* FROM car c LEFT JOIN contract_cars cc ON c.id = cc.cars_id JOIN rental r, contract co
//    WHERE c.name="mazda" AND ((cc.cars_id IS NULL) OR (co.rental_id=r.id AND cc.contract_id=co.id AND CURRENT_DATE > r.end_rent))

    List<Car> findAll(Specification<Car> specification);

    @Query("SELECT c FROM Car c WHERE c.name LIKE %?1%")
    Page<Car> findByName(String name, Pageable pageable);
}
