package com.example.carrental.utils;

import com.example.carrental.model.Car;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarSpecification {
    public static Specification<Car> getSpecs(String name, String licensePlate, String model, String type, Date startDate, Date endDate) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            System.out.println(root.get("s" + root.get("startRent")));
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (licensePlate != null && !licensePlate.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("licensePlate"), "%" + licensePlate + "%"));
            }
            if (model != null && !model.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("model"), "%" + model + "%"));
            }
            if (type != null && !type.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("type"), "%" + type + "%"));
            }
            predicates.add(criteriaBuilder.equal(root.get("startRent"), startDate));
            predicates.add(criteriaBuilder.equal(root.get("endRent"), endDate));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
