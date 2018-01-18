package kriss0101.cvdb.mappers;

import kriss0101.cvdb.commands.ContactDTO;
import kriss0101.cvdb.datamodel.Contact;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactToContactDTOMapperTest {

    @Test
    public void contactToContactDTO() {
        Contact c = new Contact();
        c.setPhone("99");
        c.setId(1L);
        c.setEmail("k@m");
        c.setAdress("vägen");

        ContactDTO dto = ContactToContactDTOMapper.contactToContactDTO(c);

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

        Contact c  = ContactToContactDTOMapper.contactDTOToContact(dto);

        assertEquals(dto.getAdress(), c.getAdress());
        assertEquals(dto.getEmail(), c.getEmail());
        assertEquals(dto.getPhone(), c.getPhone());
    }
}