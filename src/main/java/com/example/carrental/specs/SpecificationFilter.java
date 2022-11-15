package com.example.carrental.specs;

import com.example.carrental.model.*;
import com.example.carrental.model.Car_;
import com.example.carrental.model.Contract_;
import com.example.carrental.model.Rental_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SpecificationFilter {
    public static Specification<Car> getSpecsSearchCar(String name, Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.distinct(true);
            Join<Car, Rental> rentalJoin = root.join(Car_.RENTALS, JoinType.LEFT);
            Join<Rental, Contract> contractJoin = rentalJoin.join(com.example.carrental.model.Rental_.CONTRACT, JoinType.LEFT);

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(rentalJoin.get(Rental_.CAR).get(Car_.ID)),
                    criteriaBuilder.lessThan(contractJoin.get(Contract_.END_RENT), startDate)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Car> getSpecsFilterCar(String name, String licensePlate, String model, String type, Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.distinct(true);
            Join<Car, Rental> rentalJoin = root.join(Car_.RENTALS, JoinType.LEFT);
            Join<Rental, Contract> contractJoin = rentalJoin.join(Rental_.CONTRACT, JoinType.LEFT);

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

            predicates.add(criteriaBuilder.or(criteriaBuilder.isNull(rentalJoin.get(Rental_.CAR).get(Car_.ID)),
                    criteriaBuilder.lessThan(contractJoin.get(Contract_.END_RENT), startDate)));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Customer> getSpecsFilterCustomer(String key) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (key != null && !key.isEmpty()) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + key + "%"),
                        criteriaBuilder.like(root.get("address"), "%" + key + "%"),
                        criteriaBuilder.like(root.get("tel"), "%" + key + "%")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
