package org.example.rbc.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.rbc.domain.Bet;
import org.example.rbc.repository.BetRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BetService implements Dao<Bet, Long>{

    private final BetRepository repository;

    public Bet findById(Long id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    public List<Bet> findAll() {
        List<Bet> betList = new ArrayList<>();
        repository.findAll().forEach(betList::add);
        return betList;
    }

    public Bet save(Bet bet) {
        return repository.save(bet);
    }

    public List<Bet> saveAll(List<Bet> races) {
        List<Bet> betList = new ArrayList<>();
        repository.saveAll(races).forEach(betList::add);
        return betList;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}