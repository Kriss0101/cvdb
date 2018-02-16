package cvdb.browser.controllers;


import cvdb.browser.commands.ResumeDTO;
import cvdb.browser.services.BrowserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"","/","/resumes","resumes"})
@Slf4j
public class ResumeBrowserController {

    @Value("${url.api.resumes}")
    private String url_api_resumes;
    @Value("${url.api.resumes.search}")
    private String url_api_resumes_search;

    
    private BrowserService browserService;
    
    public ResumeBrowserController(BrowserService searchService) {
        this.browserService = searchService;
    }

    @GetMapping({"", "/","search"})
    public String search(Model model, @RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "lastName", required = false) String lastName, @RequestParam(value = "freeText", required = false) String freeText) {

    	List<ResumeDTO> resumes = browserService.search(firstName, lastName, freeText);
    	
        model.addAttribute("resumes", resumes);

        log.info("*****Found resumes******");
        resumes.stream().forEach(r -> log.info(r.toString()));

        return "searchResumes";
    }
    @GetMapping("{id}")
    public String getById(Model model, @PathVariable Long id) {

        ResumeDTO resume = browserService.getById(id);
        model.addAttribute("Resume", resume);

        log.info("*****Found resume******");
        log.info(resume.toString());

        return "showResume";
    }



    
    @GetMapping("{id}/show")
    public String edit(Model model, @PathVariable("id") Long id) {
        ResumeDTO resume = browserService.getById(id);
        model.addAttribute("resume", resume);
        return "showResume";
    }

    
    
    @PostMapping("update")
    public String update(@ModelAttribute("resume") ResumeDTO resumeDTO) {
    	ResumeDTO savedResumeDTO = browserService.update(resumeDTO);        
        return "redirect:/resumes/" + savedResumeDTO.getId() +"/show";
    }
    
    


}
