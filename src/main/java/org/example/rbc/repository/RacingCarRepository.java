package org.example.rbc.repository;

import org.example.rbc.domain.RacingCar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacingCarRepository
        extends CrudRepository<RacingCar,Long> {
}
