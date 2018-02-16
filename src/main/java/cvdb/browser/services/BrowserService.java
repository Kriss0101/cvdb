package cvdb.browser.services;


import cvdb.browser.commands.ResumeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrowserService {

	List<ResumeDTO> search(String firstName, String lastName, String freeText);

	ResumeDTO update(ResumeDTO dto);

	ResumeDTO getById(Long id);
	
}
