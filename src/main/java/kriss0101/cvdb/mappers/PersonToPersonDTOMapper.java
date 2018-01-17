package kriss0101.cvdb.mappers;

import kriss0101.cvdb.commands.ContactDTO;
import kriss0101.cvdb.datamodel.Contact;
import org.mapstruct.Mapper;

@Mapper
public interface PersonToPersonDTOMapper {

    ContactDTO contactToContactDTO(Contact contact);
    Contact contactDTOToContact(ContactDTO dto);

}
