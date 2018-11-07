package cvdb.api.services;

import cvdb.api.datamodel.Person;
import cvdb.api.repositories.PersonRepository;
import cvdb.api.exceptions.UpdateResourceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {


    PersonRepository repo;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.repo = personRepository;
    }

    @Override
    public List<Person> getPersons() {
        ArrayList<Person> people = new ArrayList<Person>();
        repo.findAll().spliterator().forEachRemaining(people::add);
        return people;
    }



    @Override
    public Optional<Person> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Person createPerson(Person person) {
        return repo.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        if (person.getId() == null) {
            throw new UpdateResourceException("Cannot update person. Id must not be null.");
        }
        return repo.save(person);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
