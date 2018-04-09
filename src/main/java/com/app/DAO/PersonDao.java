package com.app.DAO;

import com.app.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {
    void add(Person person);

    void update(Person person);

    void delete(Integer id);

    Optional<Person> findOneById(Integer id);

    List<Person> findAll();
}
