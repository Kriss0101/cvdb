package kriss0101.cvdb.client.controllers;

import kriss0101.cvdb.api.commands.SearchCriteriaDTO;
import kriss0101.cvdb.api.datamodel.Resume;
import kriss0101.cvdb.api.repositories.PersonRepository;
import kriss0101.cvdb.api.repositories.ResumeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResumeControllerIT {


    private String uri_resume_search = "http://localhost:8080/";


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private PersonRepository personRepository;

    private ResumesTestFixture resumesTestFixture;

    Logger logger = LoggerFactory.getLogger(ResumeControllerIT.class.getName());

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        resumesTestFixture = new ResumesTestFixture(resumeRepository, personRepository);
        resumesTestFixture.storeResumeTestData();
    }

    @Test
    public void testWebAppContext() {
        assertThat(webApplicationContext).isNotNull();
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertThat(servletContext).isNotNull();

    }
    @Test
    public void testIndexPage_ExpectStatusOk() throws Exception {

        mockMvc.perform(get(uri_resume_search)
                .contentType(MediaType.APPLICATION_JSON))

                //Then
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("searchResumes"));

        }

    @Test
    public void testSearch() throws Exception {

        // given
        SearchCriteriaDTO searchCriteriaDTO = new SearchCriteriaDTO();
        searchCriteriaDTO.setFirstName("Pell");
        searchCriteriaDTO.setLastName("");
        searchCriteriaDTO.setFreeText("");

        logger.info("api host: " + uri_resume_search);

        // when
        MvcResult res = mockMvc.perform(get(uri_resume_search)
                .param("firstName",searchCriteriaDTO.getFirstName())
                .param("lastName",searchCriteriaDTO.getLastName())
                .param("freeText", searchCriteriaDTO.getFreeText())
                .contentType(MediaType.APPLICATION_JSON)
        )
                //Then
                .andExpect(model().attributeExists("resumes"))
                .andReturn();

        List<Resume> resumes = (List<Resume>) res.getModelAndView().getModelMap().get("resumes");
        assertThat(resumes).isNotEmpty();

    }

    @Test
    public void testGetById_Expect200() throws Exception {
        // when
        String uri_resume_by_id = "http://localhost:8080/resumes";
        Long id = 1L;
        MvcResult res = mockMvc.perform(get(uri_resume_by_id + "/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //String jsonString = res.getResponse().getContentAsString();
        //asserThat(jsonPath(jsonString, "")


    }
}
