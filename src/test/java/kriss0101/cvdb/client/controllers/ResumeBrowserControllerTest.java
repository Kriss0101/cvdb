package kriss0101.cvdb.client.controllers;




import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import kriss0101.cvdb.TestConfig;
import kriss0101.cvdb.api.commands.ResumeDTO;
import kriss0101.cvdb.browser.controllers.ResumeBrowserController;
import kriss0101.cvdb.browser.services.BrowserService;



@RunWith(SpringRunner.class)
//@ContextConfiguration(//classes= {})
@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class ResumeBrowserControllerTest {


	@Value("${url.api.resumes}")
    private  String url_api_resumes;

	private ResumeBrowserController resumeController;

    private MockMvc mvc;

    
    private MockRestServiceServer mockRestServer;
    
    @Mock
    private BrowserService browserService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        RestTemplate restTemplate = new RestTemplateBuilder().build();
         
        resumeController = new ResumeBrowserController(browserService);

        mockRestServer = MockRestServiceServer.createServer(restTemplate);

        mvc=MockMvcBuilders.standaloneSetup(resumeController).build();
        
        setUpMockServiceReturnEmptyResult();        
		
    }   

	private void setUpMockServiceReturnEmptyResult() {
    	when(browserService.search(anyString(), anyString(), anyString())).thenReturn(Collections.emptyList());
		
	}

	@Test
    public void testIndexEndPoint() throws Exception {

        // Given		
        setUpMockServiceReturnEmptyResult();

        // When
        mvc.perform(get("/"))
                // Then
                .andExpect(status().isOk());

        mockRestServer.verify();


    }

//    private void mockAPICall_GetResumeByParameters(MockRestServiceServer mockRestServer) {
//        List<ResumeDTO> resumes = Arrays.asList(new ResumeDTO());
//        String jsonExpectedContent = new Gson().toJson(resumes);
//        String urlPattern = UriComponentsBuilder.fromUriString("/resumes").queryParam("firstName").queryParam("lastName").queryParam("freeText").build().toUriString();
//        mockRestServer.expect(MockRestRequestMatchers.requestTo(Matchers.endsWith(urlPattern)))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withSuccess(jsonExpectedContent, MediaType.APPLICATION_JSON));
//    }

    @Test
    public void testGetResumeEndPoint_Expect200() throws Exception {

    	 // Given
    	Long id = 1L;
        ResumeDTO resumeDTO= new ResumeDTO();
        resumeDTO.setId(id);
        setUpMockGetServiceReturnResult(resumeDTO);

        // When
        mvc.perform(get("/resumes/"+ id))
                // Then
                .andExpect(status().isOk());

        mockRestServer.verify();

    }
	private void setUpMockGetServiceReturnResult(ResumeDTO dto) {
    	when(browserService.getById(anyLong())).thenReturn(dto);
		
	}
    @Test
    public void testUpdateResume() throws Exception {

        // Given
        ResumeDTO resumeDTO= new ResumeDTO();
        resumeDTO.setId(6L);
        
        setUpMockUpdateServiceReturnResult(resumeDTO);
        // When
        String jsonExpectedContent = new Gson().toJson(resumeDTO);
        mvc.perform(post("/resumes/update/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonExpectedContent))
                // Then
                .andExpect(status().is3xxRedirection());
                //.andExpect(redirectedUrl("/resumes/6/show"));

        mockRestServer.verify();

    }
    
    private void setUpMockUpdateServiceReturnResult(ResumeDTO resumeDTO) {    	
		when(browserService.update(any(ResumeDTO.class))).thenReturn(resumeDTO);
		
	}


}