package kriss0101.cvdb.mappers;

import kriss0101.cvdb.commands.PersonDTO;
import kriss0101.cvdb.datamodel.Person;

public class PersonToPersonDTOMapper {

    public static PersonDTO personToPersonDTO(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();

        personDTO.setFirstName( person.getFirstName() );
        personDTO.setLastName( person.getLastName() );

        personDTO.setContactDTO(
                ContactToContactDTOMapper.contactToContactDTO(person.getContact()));

        personDTO.setPresentationDTO(
                PresentationToPresentationDTOMapper.presentationToPresentationDTO(person.getPresentation())
        );


        return personDTO;
    }


    public static Person personDTOToPerson(PersonDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Person person = new Person();

        person.setFirstName( dto.getFirstName() );
        person.setLastName( dto.getLastName() );

        person.setContact(ContactToContactDTOMapper.contactDTOToContact(dto.getContactDTO()));

        person.setPresentation(
                PresentationToPresentationDTOMapper.presentationDTOToPresentation(
                        dto.getPresentationDTO()
                )
        );

        return person;
    }
}
