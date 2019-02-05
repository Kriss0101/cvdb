package cvdb.api.services;

import java.util.List;
import java.util.Optional;

import cvdb.api.domain.Resume;
import cvdb.api.domain.SearchCriteria;

public interface ResumeService {



    List<Resume> getResumes();

    Optional<Resume> getById(Long id);

    List<Resume> search(SearchCriteria criteria);

    Resume save(Resume resume);

    Resume update(Resume resume);
}
