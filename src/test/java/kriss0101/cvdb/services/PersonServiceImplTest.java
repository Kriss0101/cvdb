package kriss0101.cvdb.services;

import kriss0101.cvdb.datamodel.Contact;
import kriss0101.cvdb.datamodel.Person;
import kriss0101.cvdb.datamodel.Presentation;
import kriss0101.cvdb.repositories.PersonRepository;
import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class PersonServiceImplTest {

    @Mock
    PersonRepository repository;

    PersonService service;

    private List<Person> persons;

    @Before
    public void setUp() throws Exception {
        // Given
        MockitoAnnotations.initMocks(this);

        // When
        service = new PersonServiceImpl(repository);

        // Then
        persons = getMockedPersonCollection();

    }

    private List<Person> getMockedPersonCollection() {
        Person p1 = new Person(1L,"Pelle","Persson",
                new Contact(null,"vägen 2","pelle@gmail.com","234234"),
                new Presentation("Skilled developer", "I am very proficient in OO design and programming."));
        Person p2 = new Person(2L,"Kalle","Karlsson",
                new Contact(null,"vägen 2","kalle@gmail.com","234234"),
                new Presentation("Very Skilled developer", "I am very proficient in OO design and programming."));
        Person p3 = new Person(3L,"Jonas","Persson",
                new Contact(null,"vägen 2","jonas@gmail.com","234234"),
                new Presentation("NOt so skilled developer", "I am very proficient in OO design and programming."));               ;

    return Arrays.asList(p1,p2,p3);
};

    @Test
    public void testGetPersonsSize3() {
        when(repository.findAll()).thenReturn(persons);

        List<Person> found = service.getPersons();

        assertThat(found.size()).isEqualTo(3);

    }

    @Test
    public void testGetById() {
        Person p1 = persons.get(0);
        when(repository.findOne(anyLong())).thenReturn(p1);

        Optional<Person> found = service.getById(999L);

        assertThat(found.get()).isEqualTo(p1);
    }
}