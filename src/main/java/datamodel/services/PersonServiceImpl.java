package datamodel.services;

import datamodel.Person;
import datamodel.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonServiceImpl implements PersonService {


    PersonRepository repo;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.repo = personRepository;
    }

    @Override
    public List<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<Person>();
        repo.findAll().spliterator().forEachRemaining(persons::add);
        return persons;
    }

    @Override
    public Optional<Person> getById(Long id) {
        return Optional.of(repo.findOne(id));
    }
}
