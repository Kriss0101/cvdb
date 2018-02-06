package kriss0101.cvdb.client.controllers;

import kriss0101.cvdb.api.commands.ResumeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Controller
public class ResumeController {

    public static final String ATTRIBUTE_RESUMES = "resumes";
    public static final String URL_API_RESUMES = "http://localhost:8080/api/resumes";
    public static final String URL_API_SEARCH = "http://localhost:8080/api/resumes/search";
    public static final String URL_API_BY_ID = "http://localhost:8080/api/resumes";



    Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Autowired
    private RestTemplate restTemplate;

    public ResumeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping({"", "/", "resumes/search"})
    public String search(Model model, @RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName, @RequestParam(value = "freeText", required = false) String freeText) {

        List<ResumeDTO> resumes = getResumesBy(firstName, lastName, freeText);
        model.addAttribute(ATTRIBUTE_RESUMES, resumes);

        logger.info("*****Found resumes******");
        resumes.stream().forEach(r -> logger.info(r.toString()));

        return "searchResumes";
    }
    @GetMapping("resumes/{id}")
    public String getById(Model model, @PathVariable Long id) {

        ResumeDTO resume = getResumeBy(id);
        model.addAttribute("Resume", resume);

        logger.info("*****Found resume******");
        logger.info(resume.toString());

        return "editResume";
    }



    private List<ResumeDTO> getResumesBy(String firstName, String lastName, String freeText) {

        String rest_uri = UriComponentsBuilder.fromUriString(URL_API_SEARCH)
                .queryParam("firstName", firstName)
                .queryParam("lastName", lastName)
                .queryParam("freeText", freeText).build().toUriString();

        logger.info("ResumeController.search calling rest api at " + rest_uri);
        ResponseEntity<ResumeDTO[]> res = restTemplate.exchange(rest_uri, HttpMethod.GET, null, ResumeDTO[].class);

        return Arrays.asList(res.getBody());
    }

    @GetMapping("resumes/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        ResumeDTO resume = getResumeBy(id);
        model.addAttribute("resume", resume);
        return "editResume";
    }

    private ResumeDTO getResumeBy(Long id) {
        String rest_uri = UriComponentsBuilder.fromUriString(URL_API_BY_ID + "/" +id)
                .build().toUriString();

        logger.info("ResumeController.search calling rest api at " + rest_uri);
        ResponseEntity<ResumeDTO> res = restTemplate.exchange(rest_uri, HttpMethod.GET, null, ResumeDTO.class);
        return res.getBody();
    }
    @PostMapping("resumes/update")
    public String update(@ModelAttribute("resume") ResumeDTO resumeDTO) {
        ResumeDTO savedResumeDTO = updateResume(resumeDTO);
        return "redirect:/resumes/" + savedResumeDTO.getId() +"/edit";
    }
    private ResumeDTO updateResume(ResumeDTO resumeDTO) {

        String rest_uri = UriComponentsBuilder.fromUriString(URL_API_RESUMES)
                    .build().toUriString();

        logger.info("ResumeController.update calling rest api at " + rest_uri);
        HttpEntity<ResumeDTO> requestEntity = new HttpEntity(resumeDTO);
        ResponseEntity<ResumeDTO> res = restTemplate.exchange(rest_uri, HttpMethod.PUT,requestEntity, ResumeDTO.class);

        return res.getBody();
    }


}
