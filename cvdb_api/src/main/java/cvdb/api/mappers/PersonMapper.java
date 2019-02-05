

package cvdb.api.mappers;


import cvdb.api.commands.PersonDTO;
import cvdb.api.domain.Person;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PersonMapper {


     PersonDTO PersonToPersonDTO(Person person);
     Person PersonDTOToPerson(PersonDTO dto);




}
