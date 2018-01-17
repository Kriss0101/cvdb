package kriss0101.cvdb.converters;

import kriss0101.cvdb.commands.PersonDTO;
import kriss0101.cvdb.datamodel.Contact;
import kriss0101.cvdb.datamodel.Person;
import kriss0101.cvdb.mappers.PersonToPersonDTOMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

public class PersonToPersonDTOConverterTest {


    @Test
    public void testConvertPersonToPersonDTO() {

        Person p1 = Person.builder().firstName("Kris").lastName("Krisson")
                .contact(
                    Contact.builder().adress("vägen 3").email("kris@gmail.com").id(1L).phone("9999").build()
                ).build();

        PersonToPersonDTOMapper mapper = Mappers.getMapper(PersonToPersonDTOMapper.class);
        PersonDTO pDTO= mapper.



    }
}