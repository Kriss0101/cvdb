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

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Autowired
    private RestTemplate restTemplate;

    public ResumeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping({"", "/", "resumes","resumes/search"})
    public String search(Model model, @RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName, @RequestParam(value = "freeText", required = false) String freeText) {

        List<ResumeDTO> resumes = requestResumes_fromAPI(firstName, lastName, freeText);
        model.addAttribute("resumes", resumes);

        logger.info("*****Found resumes******");
        resumes.stream().forEach(r -> logger.info(r.toString()));

        return "searchResumes";
    }
    @GetMapping("resumes/{id}")
    public String getById(Model model, @PathVariable Long id) {

        ResumeDTO resume = requestResumeBy_FromAPI(id);
        model.addAttribute("Resume", resume);

        logger.info("*****Found resume******");
        logger.info(resume.toString());

        return "editResume";
    }



    private List<ResumeDTO> requestResumes_fromAPI(String firstName, String lastName, String freeText) {

        String rest_uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/resumes/search")
                .queryParam("firstName", firstName)
                .queryParam("lastName", lastName)
                .queryParam("freeText", freeText).build().toUriString();

        logger.info("ResumeController.search calling rest api at " + rest_uri);
        ResponseEntity<ResumeDTO[]> res = restTemplate.exchange(rest_uri, HttpMethod.GET, null, ResumeDTO[].class);

        return Arrays.asList(res.getBody());
    }

    @GetMapping("resumes/{id}/show")
    public String edit(Model model, @PathVariable("id") Long id) {
        ResumeDTO resume = requestResumeBy_FromAPI(id);
        model.addAttribute("resume", resume);
        return "showResume";
    }

    private ResumeDTO requestResumeBy_FromAPI(Long id) {
        String rest_uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/resumes/" +id)
                .build().toUriString();

        logger.info("ResumeController.search calling rest api at " + rest_uri);
        ResponseEntity<ResumeDTO> res = restTemplate.exchange(rest_uri, HttpMethod.GET, null, ResumeDTO.class);
        return res.getBody();
    }
    @PostMapping("resumes/update")
    public String update(@ModelAttribute("resume") ResumeDTO resumeDTO) {
        ResumeDTO savedResumeDTO = requestUpdateResume_fromAPI(resumeDTO);
        return "redirect:/resumes/" + savedResumeDTO.getId() +"/edit";
    }
    private ResumeDTO requestUpdateResume_fromAPI(ResumeDTO resumeDTO) {

        String rest_uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/resumes/")
                    .build().toUriString();

        logger.info("ResumeController.update calling rest api at " + rest_uri);
        HttpEntity<ResumeDTO> requestEntity = new HttpEntity(resumeDTO);
        ResponseEntity<ResumeDTO> res = restTemplate.exchange(rest_uri, HttpMethod.PUT,requestEntity, ResumeDTO.class);

        return res.getBody();
    }


}
