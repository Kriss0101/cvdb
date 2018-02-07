package kriss0101.cvdb.api.controllers;

import kriss0101.cvdb.api.commands.ResumeDTO;
import kriss0101.cvdb.api.datamodel.Resume;
import kriss0101.cvdb.api.datamodel.SearchCriteria;
import kriss0101.cvdb.api.exceptions.ResourceNotFoundException;
import kriss0101.cvdb.api.mappers.ResumeMapper;
import kriss0101.cvdb.api.services.ResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/resumes")
public class ResumeRestController {

    @Autowired
    private ResumeService service;
    ResumeMapper mapper = ResumeMapper.INSTANCE;
    public ResumeRestController(ResumeService service) {
        this.service = service;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ResumeDTO> getAllResumes() {
        return service.getResumes().stream().map(mapper::resumeToResumeDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResumeDTO getById(@PathVariable Long id) {
        Optional<Resume> ret = service.getById(id);
        if (ret.isPresent()) {
            return mapper.resumeToResumeDTO(ret.get());
        } else {
            throw new ResourceNotFoundException("Resume was not found: id = " + id);
        }
    }

    @PutMapping("")
    public ResumeDTO updateResume(@RequestBody ResumeDTO resumeDTO) {
        ResumeMapper mapper = ResumeMapper.INSTANCE;
        Resume resume = mapper.resumeDTOToResume(resumeDTO);

        Resume resumeUpdated = service.update(resume);
        ResumeDTO ret = mapper.resumeToResumeDTO(resumeUpdated);
        return ret;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResumeDTO saveResume(@Valid @RequestBody ResumeDTO resumeDTO) {
        return mapper.resumeToResumeDTO(service.save(mapper.resumeDTOToResume(resumeDTO)));
    }

    @GetMapping("/search")
    public List<ResumeDTO> search(@Valid @RequestParam(value = "firstName",required = false) String firstName, @RequestParam(value="lastName",required = false) String lastName, @RequestParam(value="freeText",required = false) String freeText) {
        Logger logger = LoggerFactory.getLogger(this.getClass().getName());

        SearchCriteria searchCriteria = SearchCriteria.builder().firstName(firstName).lastName(lastName).freeText(freeText).build();
        List<Resume> resumes = service.search(searchCriteria);

        return resumes.stream().map(mapper::resumeToResumeDTO).collect(Collectors.toList());
    }
}
