package kriss0101.cvdb.controllers;

import kriss0101.cvdb.datamodel.Person;
import kriss0101.cvdb.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return service.getPersons();
    }


}
