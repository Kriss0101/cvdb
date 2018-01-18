package kriss0101.cvdb.controllers;

import kriss0101.cvdb.datamodel.Person;
import kriss0101.cvdb.exceptions.ResourceNotFoundException;
import kriss0101.cvdb.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return service.getPersons();
    }
    @GetMapping("/persons/{id}")
    public Person getPersonById(@PathVariable  Long id) {
        Optional<Person> p = service.getById(id);
        if (!p.isPresent()) {
            throw new ResourceNotFoundException("Could not retrieve person with id " + (id == null ? "null" : id));
        }
        else {
            return p.get();
        }

}


}
