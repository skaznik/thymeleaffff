package com.example.thymeleaf.skierowanie.dao;

import com.example.thymeleaf.skierowanie.model.Miejsce;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiejsceDAO  extends CrudRepository<Miejsce, Integer> {
}
