package kriss0101.cvdb.api.services;

import kriss0101.cvdb.api.datamodel.Resume;
import kriss0101.cvdb.api.datamodel.SearchCriteria;

import java.util.List;
import java.util.Optional;

public interface ResumeService {



    List<Resume> getResumes();

    Optional<Resume> getById(Long id);

    List<Resume> search(SearchCriteria criteria);

    Resume save(Resume resume);

    Resume update(Resume resume);
}
