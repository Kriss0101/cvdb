package kriss0101.cvdb.browser.services;

import java.util.List;

import org.springframework.stereotype.Service;

import kriss0101.cvdb.api.commands.ResumeDTO;
import kriss0101.cvdb.api.datamodel.Resume;

@Service
public interface BrowserService {

	List<ResumeDTO> search(String firstName, String lastName, String freeText);

	ResumeDTO update(ResumeDTO dto);

	ResumeDTO getById(Long id);

	
	
}
