package kriss0101.cvdb.mappers;

import kriss0101.cvdb.commands.ContactDTO;
import kriss0101.cvdb.commands.PersonDTO;
import kriss0101.cvdb.datamodel.Contact;
import kriss0101.cvdb.datamodel.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonToPersonDTOMapper {

    PersonToPersonDTOMapper INSTANCE = Mappers.getMapper(PersonToPersonDTOMapper.class);

    ContactDTO contactToContactDTO(Contact contact);
    Contact contactDTOToContact(ContactDTO contactDTO);

    PersonDTO personToPersonDTO(Person person);
    Person personDTOToPerson(PersonDTO dto);

}
