package kriss0101.cvdb.mappers;

import kriss0101.cvdb.commands.ContactDTO;
import kriss0101.cvdb.commands.PersonDTO;
import kriss0101.cvdb.datamodel.Contact;
import kriss0101.cvdb.datamodel.Person;
import org.mapstruct.Mapper;

public interface PersonToPersonDTOMapper {

    ContactDTO contactToContactDTO(Contact contact);
    Contact contactDTOToContact(ContactDTO dto);

    PersonDTO personToPersonDTO(Person person);
    Person personDTOToPerson(PersonDTO dto);

}
