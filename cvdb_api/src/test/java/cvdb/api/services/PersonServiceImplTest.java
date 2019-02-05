package cvdb.api.services;

import cvdb.api.services.PersonService;
import cvdb.api.services.PersonServiceImpl;
import cvdb.api.domain.Contact;
import cvdb.api.domain.Person;
import cvdb.api.repositories.PersonRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PersonServiceImplTest {

    @Mock
    PersonRepository repository;

    PersonService service;

    private List<Person> people;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        service = new PersonServiceImpl(repository);

        people = getMockedPersonCollection();

    }

    private List<Person> getMockedPersonCollection() {
        Person p1 = Person.builder().firstName("Pelle").lastName("Persson")
                .contact(Contact.builder().adress("vägen 2").email("pelle@gmail.com").phone("234234").build())
                        .build();

        Person p2 = Person.builder().build();
        Person p3 = Person.builder().build();


    return Arrays.asList(p1,p2,p3);
};

    @Test
    public void testGetAllPersons_ShouldHaveSizeThree() {
        // Given
        when(repository.findAll()).thenReturn(people);

        // When
        List<Person> found = service.getPersons();

        // Then
        assertThat(found.size()).isEqualTo(3);

    }

    @Test
    public void testGetById_ShouldExist() {
        // Given
        Person p1 = people.get(0);
        when(repository.findById(anyLong())).thenReturn(Optional.of(p1));

        // When
        Optional<Person> found = service.getById(999L);

        // Then
        assertThat(found.get()).isEqualTo(p1);
    }



    @Test
    public void createPerson() {
        // Given
        Person p = people.get(0);
        when(repository.save(any(Person.class))).thenReturn(p);

        // When
        Person pret = service.createPerson(p);

        // Then
        assertThat(pret).isEqualTo(p);
        verify(repository, times(1)).save(p);
    }

    @Test(expected = RuntimeException.class)
    public void updatePerson_withInvalidId() {
        // Given
        Person p = people.get(0);
        when(repository.save(any(Person.class))).then((Answer<Person>) invocation -> {
                    Person person = invocation.getArgument(0);
                    person.setId(10L);
                    return person;
                });
        p.setId(null);

        // When
        Person pret = service.updatePerson(p);

        // Then
        assertThat(pret).isEqualTo(p);
        assertThat(pret.getId()).isEqualTo(10L);
        verify(repository, times(1)).save(p);
    }
    @Test
    public void updatePerson_ValidId() {
        // Given
        Person p = people.get(0);
        when(repository.save(any(Person.class))).then((Answer<Person>) invocation -> {
            Person person = invocation.getArgument(0);
            person.setId(10L);
            return person;
        });
        p.setId(10L);

        // When
        Person pret = service.updatePerson(p);

        // Then
        assertThat(pret).isEqualTo(p);
        assertThat(pret.getId()).isEqualTo(10L);
        verify(repository, times(1)).save(p);
    }
    @Test
    public void deleteById() {
        // Given
        Person p = people.get(0);

        // When
        service.deleteById(1L);

        // Then
        verify(repository, times(1)).deleteById(1L);
    }
}