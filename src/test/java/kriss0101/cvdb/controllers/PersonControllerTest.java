package kriss0101.cvdb.controllers;

import kriss0101.cvdb.datamodel.Contact;
import kriss0101.cvdb.datamodel.Person;
import kriss0101.cvdb.datamodel.Presentation;
import kriss0101.cvdb.exceptions.ResourceNotFoundException;
import kriss0101.cvdb.services.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest {


    @Mock
    PersonService service;

    @InjectMocks
    PersonController controller;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void getPersons() throws Exception {

        Person p1 = new Person();
        Person p2 = new Person();

        when(service.getPersons()).thenReturn(Arrays.asList(p1,p2));
        MvcResult res = mvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testGetPersonByIdShouldExist() throws Exception {

        // Given
        Long id = 1L;
        Person p1 = new Person();
        p1.setId(id);
        p1.setFirstName("Kalle");
        p1.setLastName("KArlsson");
        p1.setPresentation(new Presentation("short","long"));
        p1.setContact(new Contact(5L, "vägen 3", "sasf@m","999"));
        when(service.getById(anyLong())).thenReturn(Optional.of(p1));

        // When
        MvcResult personReturned = mvc.perform(get("/persons/" + id))
                // Then
                .andExpect(status().isOk())
                .andReturn();



    }
    @Test
    public void testGetPersonById_NonExisting() throws Exception {

        // Given
        Long id = 123123123L;
        Person p1 = new Person();
        p1.setId(id);
        p1.setFirstName("Kalle");
        p1.setLastName("KArlsson");
        p1.setPresentation(new Presentation("short","long"));
        p1.setContact(new Contact(5L, "vägen 3", "sasf@m","999"));
        when(service.getById(id)).thenReturn(Optional.of(p1));

        // When
        Long otherId = 92L;
        MvcResult personReturned = mvc.perform(get("/persons/" + otherId))
                // Then
                .andExpect(status().isNotFound())
                .andReturn();
    }

}
