package kriss0101.cvdb.api.controllers;

import kriss0101.cvdb.api.commands.ResumeDTO;
import kriss0101.cvdb.api.datamodel.Resume;
import kriss0101.cvdb.api.datamodel.SearchCriteria;
import kriss0101.cvdb.api.exceptions.ResourceNotFoundException;
import kriss0101.cvdb.api.mappers.ResumeMapper;
import kriss0101.cvdb.api.services.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/resumes")
public class ResumeRestController {

	
    private final ResumeMapper resumeMapper;

    private final ResumeService service;

    
    public ResumeRestController(ResumeService service, ResumeMapper resumeMapper) {
        this.service = service; this.resumeMapper = resumeMapper;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ResumeDTO> getAllResumes() {
        return service.getResumes().stream().map(resumeMapper::resumeToResumeDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResumeDTO getById(@PathVariable Long id) {
        Optional<Resume> ret = service.getById(id);
        if (ret.isPresent()) {
            return resumeMapper.resumeToResumeDTO(ret.get());
        } else {
            throw new ResourceNotFoundException("Resume was not found: id = " + id);
        }
    }

    @PutMapping("")
    public ResumeDTO updateResume(@RequestBody ResumeDTO resumeDTO) {
        
        Resume resume = resumeMapper.resumeDTOToResume(resumeDTO);
        Resume resumeUpdated = service.update(resume);        
        ResumeDTO ret = resumeMapper.resumeToResumeDTO(resumeUpdated);        
        return ret;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResumeDTO saveResume(@Valid @RequestBody ResumeDTO resumeDTO) {
        return resumeMapper.resumeToResumeDTO(service.save(resumeMapper.resumeDTOToResume(resumeDTO)));
    }

    @GetMapping("/search")
    public List<ResumeDTO> search(@Valid @RequestParam(value = "firstName",required = false) String firstName, @RequestParam(value="lastName",required = false) String lastName, @RequestParam(value="freeText",required = false) String freeText) {


        SearchCriteria searchCriteria = SearchCriteria.builder().firstName(firstName).lastName(lastName).freeText(freeText).build();
        List<Resume> resumes = service.search(searchCriteria);

        return resumes.stream().map(resumeMapper::resumeToResumeDTO).collect(Collectors.toList());
    }
}
