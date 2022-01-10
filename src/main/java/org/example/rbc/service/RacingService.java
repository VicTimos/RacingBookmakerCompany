package org.example.rbc.service;

import lombok.AllArgsConstructor;
import org.example.rbc.domain.Racing;
import org.example.rbc.repository.RacingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.CollectionTable;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class RacingService implements Dao<Racing, Long>{

    private final RacingRepository repository;

    public Racing findById(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Racing> findAll() {
        List<Racing> racingList = new ArrayList<>();
        repository.findAll().forEach(racingList::add);
        return racingList;
    }

    public Racing save(Racing racing) {
        return repository.save(racing);
    }

    public List<Racing> saveAll(List<Racing> races) {
        List<Racing> racingList = new ArrayList<>();
        repository.saveAll(races).forEach(racingList::add);
        return racingList;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
