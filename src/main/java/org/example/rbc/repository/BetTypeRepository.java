package org.example.rbc.repository;

import org.example.rbc.domain.BetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetTypeRepository
        extends CrudRepository<BetType, Long> {
}
