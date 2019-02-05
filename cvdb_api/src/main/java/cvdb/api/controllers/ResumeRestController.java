package cvdb.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cvdb.api.commands.ResumeDTO;
import cvdb.api.commands.SearchCriteriaDTO;
import cvdb.api.domain.Resume;
import cvdb.api.domain.SearchCriteria;
import cvdb.api.exceptions.ResourceNotFoundException;
import cvdb.api.mappers.ResumeMapper;
import cvdb.api.mappers.SearchCriteriaMapper;
import cvdb.api.services.ResumeService;

@RestController
@RequestMapping("api/resumes")
public class ResumeRestController {

	
    private final ResumeMapper resumeMapper;

    private final ResumeService service;

    
    public ResumeRestController(ResumeService service, ResumeMapper resumeMapper) {
        this.service = service; this.resumeMapper = resumeMapper;
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

    @GetMapping({"/",""})
    @ResponseStatus(HttpStatus.OK)
	public List<ResumeDTO> getResumesByCriteria(SearchCriteriaDTO criteria) {
        SearchCriteria searchCriteria = Mappers.getMapper(SearchCriteriaMapper.class).searchCriteriaDTOToSearchCriteria(criteria);
        if (searchCriteria.isEmpty()) {
            return getAllResumes();
        } else {
            return getFilteredResumes(searchCriteria);
        }
    }

    private List<ResumeDTO> getFilteredResumes(SearchCriteria searchCriteria) {
        return service.search(searchCriteria).stream().map(resumeMapper::resumeToResumeDTO).collect(Collectors.toList());
    }

    private List<ResumeDTO> getAllResumes() {
        return service.getResumes().stream().map(resumeMapper::resumeToResumeDTO).collect(Collectors.toList());
    }


}
