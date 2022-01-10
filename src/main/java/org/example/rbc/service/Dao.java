package org.example.rbc.service;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface Dao<T, K> {
    T findById(K id);
    List<T> findAll();
    T save(T entity);
    List<T> saveAll(List<T> entities);
    void deleteById(K id);
}
