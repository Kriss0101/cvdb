package cvdb.api.repositories;

import org.springframework.data.repository.CrudRepository;

import cvdb.api.domain.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{

}
