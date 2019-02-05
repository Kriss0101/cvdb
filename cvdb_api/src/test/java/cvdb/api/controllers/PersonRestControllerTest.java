package cvdb.api.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import cvdb.api.controllers.PersonRestController;
import cvdb.api.domain.Contact;
import cvdb.api.domain.Person;
import cvdb.api.commands.PersonDTO;
import cvdb.api.services.PersonService;
import cvdb.api.commands.PersonDTO;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonRestControllerTest {


    private final String PATH_PERSONS_API = "/api/persons";

    @Mock
    PersonService service;

    @InjectMocks
    PersonRestController controller;

    private MockMvc mvc;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void getPersons() throws Exception {

        // Given
        Person p1 = new Person();
        Person p2 = new Person();

        when(service.getPersons()).thenReturn(Arrays.asList(p1,p2));

        // When
        MvcResult res = mvc.perform(get(PATH_PERSONS_API))

                //Then
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testGetPersonById_ShouldExist() throws Exception {

        // Given
        Long id = 1L;
        Person p1 = new Person();
        p1.setId(id);
        p1.setFirstName("Kalle");
        p1.setLastName("KArlsson");
        p1.setContact(new Contact(5L, "vägen 3", "sasf@m","999"));
        when(service.getById(anyLong())).thenReturn(Optional.of(p1));

        // When
        MvcResult personReturned = mvc.perform(get(PATH_PERSONS_API+"/" + id))

                // Then
                .andExpect(status().isOk())
                .andReturn();

        String json = personReturned.getResponse().getContentAsString();
        aseertJsonMappedDTO(json, PersonDTO.class);

    }
    private void aseertJsonMappedDTO(String json, Class<?> c) {
        try {
            new Gson().fromJson(json, c);
        } catch (JsonSyntaxException e) {
            fail("Could not unmarshal Json " + json  + " could not be unmarshalled to DTO class " + c.getName());
        }
    }
    @Test
    public void testGetPersonById_NonExisting() throws Exception {

        // Given
        Long id = 123123123L;
        Person p1 = new Person();
        p1.setId(id);
        p1.setFirstName("Kalle");
        p1.setLastName("KArlsson");
        p1.setContact(new Contact(5L, "vägen 3", "sasf@m","999"));
        when(service.getById(id)).thenReturn(Optional.of(p1));

        // When
        Long otherId = 92L;
        MvcResult personReturned = mvc.perform(get(PATH_PERSONS_API+"/" + otherId))

                // Then
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
