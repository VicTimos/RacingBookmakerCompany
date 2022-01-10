package org.example.rbc.repository;

import org.example.rbc.domain.Racing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacingRepository
        extends CrudRepository<Racing, Long> {

}
