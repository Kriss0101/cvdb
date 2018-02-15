

package kriss0101.cvdb.api.mappers;


import kriss0101.cvdb.api.commands.PersonDTO;
import kriss0101.cvdb.api.datamodel.Person;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PersonMapper {


     PersonDTO PersonToPersonDTO(Person person);
     Person PersonDTOToPerson(PersonDTO dto);




}
