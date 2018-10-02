package cvdb.api.controllers;

import cvdb.api.datamodel.Person;
import cvdb.api.exceptions.ResourceNotFoundException;
import cvdb.api.services.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonRestController {

    private PersonService service;

    public PersonRestController(PersonService service) {
        this.service = service;
    }

    @GetMapping({"/",""})
    public List<Person> getPersons() {
        return service.getPersons();
    }
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable  Long id) {
        Optional<Person> p = service.getById(id);
        if (!p.isPresent()) {
            throw new ResourceNotFoundException("Could not retrieve Person with id " + (id == null ? "null" : id));
        }
        else {
            return p.get();
        }

}


}
