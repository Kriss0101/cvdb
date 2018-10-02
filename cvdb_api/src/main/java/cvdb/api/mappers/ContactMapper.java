package cvdb.api.mappers;


import cvdb.api.commands.ContactDTO;
import cvdb.api.datamodel.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ContactMapper {

    
    Contact ContactDTOToContact(ContactDTO dto);
    ContactDTO ContactToContactDTO(Contact contact);

}
