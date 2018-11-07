package cvdb;

import cvdb.api.repositories.PersonRepository;
import cvdb.api.repositories.ResumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    PersonRepository personRepository;

    @Override
    public void run(String... strings) throws Exception {

        log.info("******** Bootstraping data ************");
        ResumesTestFixture fixture = new ResumesTestFixture(resumeRepository, personRepository);
        fixture.storeResumeTestData();

        resumeRepository.findAll().forEach(e -> log.debug(e.toString()));
    }

}
