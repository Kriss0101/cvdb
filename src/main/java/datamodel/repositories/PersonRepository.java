package datamodel.repositories;

import datamodel.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long>{

}
