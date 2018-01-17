package datamodel.services;

import datamodel.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

        List<Person> getPersons();
        Optional<Person> getById(Long id);
}
