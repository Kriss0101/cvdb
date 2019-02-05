package cvdb.api.services;

import java.util.List;
import java.util.Optional;

import cvdb.api.domain.Person;

public interface PersonService {

        List<Person> getPersons();
        Optional<Person> getById(Long id);

        Person createPerson(Person person);
        Person updatePerson(Person person);
        void deleteById(Long id);
}
