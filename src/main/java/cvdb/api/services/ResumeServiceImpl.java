package cvdb.api.services;

import cvdb.api.datamodel.Resume;
import cvdb.api.datamodel.SearchCriteria;
import cvdb.api.exceptions.ResourceAlreadyExistException;
import cvdb.api.exceptions.UpdateResourceException;
import cvdb.api.repositories.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public ResumeServiceImpl(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    @Override
    public List<Resume> getResumes() {
        List<Resume> resumes = new ArrayList();
        resumeRepository.findAll().forEach(resumes::add);
        return resumes;
    }

    @Override
    public Optional<Resume> getById(Long id) {
        return resumeRepository.findById(id);
    }

    @Override
    public List<Resume> search(SearchCriteria criteria) {
        String firstName = criteria.getFirstName();
        String lastName= criteria.getLastName();
        String freeText = criteria.getFreeText();

        return resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName,lastName,freeText);

    }
    @Override
    public Resume update(Resume resume) {
        if (resume.getId() == null) {
            throw new UpdateResourceException("Cannot update resume. Id must not be null");
        }
        return resumeRepository.save(resume);
    }
    @Override
    public Resume save(Resume resume) {
        if (resumeRepository.findById(resume.getId()).isPresent()) {
            throw new ResourceAlreadyExistException("Cannot save resume. already exist. Id = " + resume.getId());
        }
        return resumeRepository.save(resume);
    }

}
