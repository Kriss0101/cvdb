package kriss0101.cvdb.api.mappers;

import kriss0101.cvdb.api.commands.ContactDTO;
import kriss0101.cvdb.api.datamodel.Contact;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.assertEquals;

public class ContactToContactDTOMapperTest {

    @Test
    public void contactToContactDTO() {
        Contact c = new Contact();
        c.setPhone("99");
        c.setId(1L);
        c.setEmail("k@m");
        c.setAdress("vägen");

        ContactDTO dto = Mappers.getMapper(ContactMapper.class).ContactToContactDTO(c);

        assertEquals(dto.getAdress(), c.getAdress());
        assertEquals(dto.getEmail(), c.getEmail());
        assertEquals(dto.getPhone(), c.getPhone());


    }

    @Test
    public void contactDTOToContact() {

        ContactDTO dto = new ContactDTO();
        dto.setPhone("99");
        dto.setEmail("k@m");
        dto.setAdress("vägen");

        Contact c  = Mappers.getMapper(ContactMapper.class).ContactDTOToContact(dto);

        assertEquals(dto.getAdress(), c.getAdress());
        assertEquals(dto.getEmail(), c.getEmail());
        assertEquals(dto.getPhone(), c.getPhone());
    }
}