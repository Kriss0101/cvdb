package kriss0101.cvdb.mappers;

import kriss0101.cvdb.commands.ContactDTO;
import kriss0101.cvdb.commands.PersonDTO;
import kriss0101.cvdb.datamodel.Contact;
import kriss0101.cvdb.datamodel.Person;
import kriss0101.cvdb.datamodel.Presentation;
import org.h2.compress.CompressNo;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;


public class PersonToPersonDTOMapperTest {

    @Test
    public void contactToContactDTO() {
        // Given
            Contact c = new Contact();
            c.setAdress("vägen 1");
            c.setEmail("kris@gmail.lcom");
            c.setId(1L);
            c.setPhone("999");

        // When
        PersonToPersonDTOMapper mapper = Mappers.getMapper(PersonToPersonDTOMapper.class);
        ContactDTO dto = mapper.contactToContactDTO(c);

        // Then
        assertThat(dto.getAdress()).isEqualTo(c.getAdress());
        assertThat(dto.getEmail()).isEqualTo(c.getEmail());
        assertThat(dto.getPhone()).isEqualTo(c.getPhone());




    }

    @Test
    public void contactDTOToContact() {
        // Given
        ContactDTO dto = new ContactDTO();
        dto.setAdress("vägen 1");
        dto.setEmail("kris@gmail.lcom");
        dto.setPhone("999");

        // When
        PersonToPersonDTOMapper mapper = Mappers.getMapper(PersonToPersonDTOMapper.class);
        Contact c  = mapper.contactDTOToContact(dto);

        // Then
        assertThat(c.getAdress()).isEqualTo(dto.getAdress());
        assertThat(c.getEmail()).isEqualTo(dto.getEmail());
        assertThat(c.getPhone()).isEqualTo(dto.getPhone());
    }

    @Test
    public void testConvertPersonToPersonDTO() {
        Contact contactDTO = new Contact(null,"vägen 3","kris@gmail.com", "9999");
        Presentation pres = new Presentation("good developer","very good developer");
        Person p1 = new Person(null,"Kris","Krisson",contactDTO, pres);

        PersonToPersonDTOMapper mapper = Mappers.getMapper(PersonToPersonDTOMapper.class);
        PersonDTO pDTO= mapper.personToPersonDTO(p1);

        assertThat(pDTO.getFirstName()).isEqualTo(p1.getFirstName());
        assertThat(pDTO.getLastName()).isEqualTo(p1.getLastName());
        assertThat(pDTO.getContactDTO()).isEqualTo(contactDTO);

        assertThat(pDTO.getPresentationDTO().getLongDescription()).isEqualTo(p1.getPresentation().getLongDescription());

    }
}