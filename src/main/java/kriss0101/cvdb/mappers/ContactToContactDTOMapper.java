package kriss0101.cvdb.mappers;

import kriss0101.cvdb.commands.ContactDTO;
import kriss0101.cvdb.commands.PersonDTO;
import kriss0101.cvdb.datamodel.Contact;
import kriss0101.cvdb.datamodel.Person;

public class ContactToContactDTOMapper {


    public static ContactDTO contactToContactDTO(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setAdress( contact.getAdress() );
        contactDTO.setPhone( contact.getPhone() );
        contactDTO.setEmail( contact.getEmail() );

        return contactDTO;
    }


    public static Contact contactDTOToContact(ContactDTO contactDTO) {
        if ( contactDTO == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setAdress( contactDTO.getAdress() );
        contact.setEmail( contactDTO.getEmail() );
        contact.setPhone( contactDTO.getPhone() );

        return contact;
    }



}
