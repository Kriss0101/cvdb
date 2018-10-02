package cvdb.browser.services;

import cvdb.browser.commands.ResumeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BrowserServiceImpl implements BrowserService{
	
	private RestTemplate restTemplate;
	
	private String url_api_resumes;
	
	
	@Autowired
	public BrowserServiceImpl(@Value("${url.api.resumes}") String url_api_resumes, RestTemplate restTemplate) {		
		this.restTemplate = restTemplate;
		this.url_api_resumes = url_api_resumes;
	}

	@Override
	public List<ResumeDTO> search(String firstName, String lastName, String freeText) {
		return searchResumes_fromAPI(firstName, lastName, freeText);
	}
	
	private List<ResumeDTO> searchResumes_fromAPI(String firstName, String lastName, String freeText) {
		
        String rest_uri = UriComponentsBuilder.fromUriString(url_api_resumes)
                .queryParam("firstName", firstName)
                .queryParam("lastName", lastName)
                .queryParam("freeText", freeText).build().toUriString();

        log.info("ResumeController.search calling rest api at " + rest_uri);
        
        ResponseEntity<ResumeDTO[]> res = restTemplate.exchange(rest_uri, HttpMethod.GET, null, ResumeDTO[].class);

        return Arrays.asList(res.getBody());
    }
	
	@Override
	public ResumeDTO update(ResumeDTO dto) {
		return requestUpdateResume_fromAPI(dto);
	}
	private ResumeDTO requestUpdateResume_fromAPI(ResumeDTO resumeDTO) {

        String rest_uri = UriComponentsBuilder.fromUriString(url_api_resumes)
                    .build().toUriString();

        log.info("ResumeController.update calling rest api at " + rest_uri);
        HttpEntity<ResumeDTO> requestEntity = new HttpEntity(resumeDTO);
        ResponseEntity<ResumeDTO> res = restTemplate.exchange(rest_uri, HttpMethod.PUT,requestEntity, ResumeDTO.class);

        return res.getBody();
    }

	@Override
	public ResumeDTO getById(Long id) {
		return requestGetResumeBy_FromAPI(id);
	}
	
	private ResumeDTO requestGetResumeBy_FromAPI(Long id) {
        String rest_uri = UriComponentsBuilder.fromUriString(url_api_resumes + "/" +id)
                .build().toUriString();

        log.info("ResumeController.search calling rest api at " + rest_uri);
        ResponseEntity<ResumeDTO> res = restTemplate.exchange(rest_uri, HttpMethod.GET, null, ResumeDTO.class);
        return res.getBody();
    }


}
