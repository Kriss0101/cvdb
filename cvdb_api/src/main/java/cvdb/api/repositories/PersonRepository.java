package cvdb.api.repositories;

import cvdb.api.datamodel.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long>{

}
