package datamodel.services;

import datamodel.Person;
import datamodel.Presentation;
import datamodel.repositories.PersonRepository;
import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;

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
        MockitoAnnotations.initMocks(this);

        service = new PersonServiceImpl(repository);

        persons = getMockedPersonCollection();

    }

    private List<Person> getMockedPersonCollection() {
        Person p1 = Person.builder().firstName("Pelle").lastName("Persson").id(1L)
                .presentation(new Presentation("Skilled developer", "I am very proficient in OO design and programming.")
                ).build();
            ;
    Person p2 = Person.builder().firstName("Kalle").lastName("Karlsson").id(2L)
            .presentation(new Presentation("Junior developer", "I am a beginner but very smart.")
            ).build();
    Person p3 = Person.builder().firstName("Lisa").lastName("Larsson").id(3L)
            .presentation(new Presentation("Highly focused developer", "I am very focused.")
            ).build();

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