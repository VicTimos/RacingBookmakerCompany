package org.example.rbc.repository;

import org.example.rbc.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository
        extends CrudRepository<Car, Long> {
}
