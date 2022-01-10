package org.example.rbc.repository;

import org.example.rbc.domain.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository
        extends CrudRepository<Bet, Long> {}
