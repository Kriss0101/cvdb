package kriss0101.cvdb.api.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import kriss0101.cvdb.api.datamodel.*;
import kriss0101.cvdb.api.services.ResumeService;
import kriss0101.cvdb.client.commands.ResumeDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResumeRestControllerTest {


    public static final String RESUME_API = "/api/resumes";

    @Mock
    ResumeService resumeService;

    @InjectMocks
    ResumeRestController controller;

    MockMvc mvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }


    @Test
    public void testGetAllResumes_ReturnOkAndCorrectCount() throws Exception {

        // Given
        Resume r1 = Resume.builder().build();
        Resume r2 = Resume.builder().build();
        Resume r3 = Resume.builder().build();

        when(resumeService.getResumes()).thenReturn(Arrays.asList(r1,r2,r3));

        // When
        MvcResult res = mvc.perform(get(RESUME_API))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andReturn();


    }
    @Test
    public void testUpdateResume_ShouldSuccedWith200_andCallServiceWithExpectedId() throws Exception {
        // Given
        ResumeDTO resumeDTO = new ResumeDTO();
        Long expectedId= 6L;
        resumeDTO.setId(expectedId);


        when(resumeService.update(any(Resume.class))).thenReturn(new Resume());

        // When
        String  jsonContent = new Gson().toJson(resumeDTO);
        mvc.perform(put(RESUME_API)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))

                // Then
                .andExpect(status().isOk());

        ArgumentCaptor<Resume> captor = ArgumentCaptor.forClass(Resume.class);
        verify(resumeService, times(1)).update(captor.capture());

        assertThat(captor.getValue().getId()).isEqualTo(expectedId);
    }
    @Test
    public void testSaveResume_ShouldSuceed200() throws Exception {
        // Given
        Resume r1 = Resume.builder()
                .id(10L)
                .presentation(new Presentation("Very skilled developer", "I am a very very good developer"))
                .skills(Sets.newSet(new Skill("java", Grade.EXPERIENCED))).build();

        when(resumeService.save(any(Resume.class))).thenReturn(r1);

        // When
        ObjectMapper jsonMapper = new ObjectMapper();
        String  jsonContent = jsonMapper.writeValueAsString(r1);
        MvcResult res = mvc.perform(post(RESUME_API)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))

                // Then
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void testGetById_ExpectExists() throws Exception {
        // Given
        Resume r1 = Resume.builder().id(10L).build();
        when(resumeService.getById(anyLong())).thenReturn(Optional.of(r1));

        Long id = 10L;
        // When
        MvcResult res = mvc.perform(get(RESUME_API +"/"+id))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("10"))
                .andReturn();


        String json = res.getResponse().getContentAsString();
        aseertJsonMappedDTO(json, ResumeDTO.class);

    }

    private void aseertJsonMappedDTO(String json, Class<?> c) {
        try {
            new Gson().fromJson(json, c);
        } catch (JsonSyntaxException e) {
            fail("Could not unmarshal Json " + json  + " could not be unmarshalled to DTO class " + c.getName());
        }
    }

    @Test
    public void testGetById_ExpectIsNotFound() throws Exception {
        // Given
        Resume r1 = Resume.builder().id(10L).build();
        when(resumeService.getById(10L)).thenReturn(Optional.of(r1));

        Long id = 11L;
        // When
        MvcResult res = mvc.perform(get(RESUME_API+"/"+id))

                // Then
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void testSearchResumes() throws Exception {
        // Given
        Person p1 = Person.builder().id(1L).firstName("Pelle").lastName("Persson").build();
        Person p2 = Person.builder().id(2L).firstName("Kalle").lastName("Karlsson").build();


        Resume r1 = Resume.builder()
                .person(p1)
                .id(1L)
                .presentation(new Presentation("Very skilled developer", "I am a very very good developer"))
                .skills(Sets.newSet(new Skill("java", Grade.EXPERIENCED))).build();

        Resume r2 = Resume.builder()
                .person(p1)
                .id(2L)
                .presentation(new Presentation("Very good developer", "I am a very very skilled developer"))
                .skills(Sets.newSet(new Skill("java", Grade.EXPERIENCED))).build();

        Resume r3 = Resume.builder()
                .person(p2)
                .id(3L)
                .presentation(new Presentation("Very nice  developer", "I am a very very nice developer"))
                .skills(Sets.newSet(new Skill("java", Grade.EXPERIENCED))).build();



        when(resumeService.search(any(SearchCriteria.class))).thenReturn(Arrays.asList(r1, r2, r3));

        // When
        UriComponents ub = UriComponentsBuilder.fromUriString(RESUME_API+"/search").queryParam("firstName", "adfs").queryParam("lastName", "fda").queryParam("freeText", "kek").build();

        System.out.println(ub.toUriString());
        mvc.perform(get(ub.toUriString()))

                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].presentation.shortDescription", is(r1.getPresentation().getShortDescription())))
                .andReturn();

        ArgumentCaptor<SearchCriteria> argCapture = ArgumentCaptor.forClass(SearchCriteria.class);
        verify(resumeService, times(1)).search(argCapture.capture());
        SearchCriteria capturedArg = argCapture.getValue();
        assertThat(capturedArg.getFirstName()).isEqualTo("adfs");

    }

}
