

package kriss0101.cvdb.api.mappers;


import kriss0101.cvdb.api.commands.PersonDTO;
import kriss0101.cvdb.api.datamodel.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
     PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

     PersonDTO PersonToPersonDTO(Person person);
     Person PersonDTOToPerson(PersonDTO dto);




}
