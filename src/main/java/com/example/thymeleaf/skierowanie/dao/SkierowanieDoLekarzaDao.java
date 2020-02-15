package com.example.thymeleaf.skierowanie.dao;

import com.example.thymeleaf.skierowanie.model.SkierowanieDoLekarza;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SkierowanieDoLekarzaDao extends CrudRepository<SkierowanieDoLekarza, Integer> {

    @Override
    List<SkierowanieDoLekarza> findAll();

    @Override
    List<SkierowanieDoLekarza> findAllById(Iterable<Integer> integers);

    List<SkierowanieDoLekarza> findAllByPacjentAndTerminOrderById(String pacjent, Date termin);

    List<SkierowanieDoLekarza> findAllByOrderById();
}
