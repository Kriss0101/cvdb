package kriss0101.cvdb.client.controllers;

import com.google.gson.Gson;
import kriss0101.cvdb.api.commands.ResumeDTO;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@RunWith(SpringRunner.class)

public class ResumeControllerTest {


    private ResumeController resumeController;

    private MockMvc mvc;

    @Autowired
    private MockRestServiceServer mockRestServer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        RestTemplate restTemplate = new RestTemplateBuilder().build();
        resumeController = new ResumeController(restTemplate);

        mockRestServer = MockRestServiceServer.createServer(restTemplate);

        mvc=MockMvcBuilders.standaloneSetup(resumeController).build();
    }

    @Test
    public void testIndexEndPoint() throws Exception {

        // Given
        mockAPICall_GetResumeByParameters(mockRestServer);


        // When
        mvc.perform(get("/"))
                // Then
                .andExpect(status().isOk());

        mockRestServer.verify();


    }

    private static void mockAPICall_GetResumeByParameters(MockRestServiceServer mockRestServer) {
        List<ResumeDTO> resumes = Arrays.asList(new ResumeDTO());
        String jsonExpectedContent = new Gson().toJson(resumes);
        mockRestServer.expect(requestTo("http://localhost:8080/api/resumes/search?firstName&lastName&freeText"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonExpectedContent, MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetResumeEndPoint_Expect200() throws Exception {

        // Given
        Long id = 1L;
        mockAPICall_GetResumeById(mockRestServer, id);

        // When
        mvc.perform(get("/resumes/"+ id)
        )
                // Then
                .andExpect(status().isOk());

        mockRestServer.verify();

    }
    @Test
    public void testEditEndPoint_Expect200() throws Exception {

        Long id = 1L;
        mockAPICall_GetResumeById(mockRestServer,id);

        // When
        mvc.perform(get("/resumes/{id}/edit", id)
        )
                // Then
                .andExpect(status().isOk());

        mockRestServer.verify();

    }

    private static void mockAPICall_GetResumeById(MockRestServiceServer mockRestServer, Long id) {
        ResumeDTO resume = new ResumeDTO();
        String jsonExpectedContent = new Gson().toJson(resume);
        mockRestServer.expect(requestTo("http://localhost:8080/api/resumes/" + id))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonExpectedContent, MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateResume() throws Exception {

        // Given
        ResumeDTO resumeDTO= new ResumeDTO();
        resumeDTO.setId(6L);
        String jsonExpectedContent = mockAPICall_updateResume(resumeDTO, mockRestServer);

        // When
        mvc.perform(post("/resumes/update/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonExpectedContent))
                // Then
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/resumes/6/edit"));

        mockRestServer.verify();

    }

    @NotNull
    private static String mockAPICall_updateResume(ResumeDTO resume, MockRestServiceServer mockRestServer) {
        String jsonExpectedContent = new Gson().toJson(resume);

        mockRestServer.expect(requestTo("http://localhost:8080/api/resumes"))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess(jsonExpectedContent, MediaType.APPLICATION_JSON));
        return jsonExpectedContent;
    }
}