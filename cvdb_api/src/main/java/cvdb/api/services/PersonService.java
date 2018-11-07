package cvdb.api.services;

import cvdb.api.datamodel.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

        List<Person> getPersons();
        Optional<Person> getById(Long id);

        Person createPerson(Person person);
        Person updatePerson(Person person);
        void deleteById(Long id);
}
