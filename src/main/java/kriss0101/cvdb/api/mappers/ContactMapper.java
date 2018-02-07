package kriss0101.cvdb.api.mappers;


import kriss0101.cvdb.api.commands.ContactDTO;
import kriss0101.cvdb.api.datamodel.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    Contact ContactDTOToContact(ContactDTO dto);
    ContactDTO ContactToContactDTO(Contact contact);

}
