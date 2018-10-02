package cvdb;

import cvdb.api.datamodel.*;
import cvdb.api.repositories.PersonRepository;
import cvdb.api.repositories.ResumeRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Test data set. Quite small for sake of first implementation.
 * @author Kristofer
 *
 */
@Slf4j
public class ResumesTestFixture {

    private final ResumeRepository resumeRepository;
    private final PersonRepository personRepository;

    public ResumesTestFixture(ResumeRepository resumeRepository, PersonRepository personRepository) {
        this.resumeRepository = resumeRepository;
        this.personRepository = personRepository;
    }

    public  void storeResumeTestData() {

        storeResumesPerson1();
        storeResumesPerson2();

        verifyData();
    }
    private  void storeResumesPerson1() {
        // Pelle
        Person pelle = Person.builder().firstName("Pelle").lastName("Persson").contact(Contact.builder().adress("vägen 1").email("asdf@asdf").phone("999").build()).build();
        personRepository.save(pelle);
        List<Education> pellesEducations = Arrays.asList(
                Education.builder().fromYear(1990).toYear(1999).description("javakurs").build(),
                Education.builder().fromYear(2000).toYear(2010).description("c++ in action").build()
        );

        List<Assignment> pellesAssignments = Arrays.asList(
                Assignment.builder().shortDescription("Matematiker").longDescription("gjorde beräkningar på balkar").fromYear(1970).toYear(1977).employer("Volvo").build(),
                Assignment.builder().shortDescription("programmerare").longDescription("gjorde en hemsida").fromYear(1990).toYear(2000).employer("webproffsen").build()
        );
        Presentation pellesPresentation = new Presentation("Briljant programmerare", "Mycket trevlig programmerare");
        List<Skill> pellesSkills = Arrays.asList(
                new Skill("Java", Grade.AUTHORITY),
                new Skill("Koka kaffe", Grade.EXPERIENCED)
        );
        Resume pelleResume1 = Resume.builder().title("Pelles första CV").person(pelle).educations(new HashSet(pellesEducations)).presentation(pellesPresentation).skills(new HashSet(pellesSkills)).assignments(new HashSet(pellesAssignments)).build();


        resumeRepository.save(pelleResume1);

        // Second resume
        List<Education> pellesEducations2 = Arrays.asList(
                Education.builder().fromYear(1990).toYear(1999).description("javakurs2").build(),
                Education.builder().fromYear(2000).toYear(2010).description("c++ in action2").build()
        );

        List<Assignment> pellesAssignments2 = Arrays.asList(
                Assignment.builder().shortDescription("Matematiker2").longDescription("gjorde beräkningar på balkar2").fromYear(1970).toYear(1977).employer("Volvo2").build(),
                Assignment.builder().shortDescription("programmerare2").longDescription("gjorde en hemsida2").fromYear(1990).toYear(2000).employer("webproffsen2").build()
        );
        Presentation pellesPresentation2 = new Presentation("Briljant programmerare2", "Mycket trevlig programmerare2");
        List<Skill> pellesSkills2 = Arrays.asList(
                new Skill("Java", Grade.AUTHORITY),
                new Skill("Koka kaffe", Grade.EXPERIENCED)
        );

        Resume pelleResume2 = Resume.builder().title("Pelles andra CV").person(pelle).educations(new HashSet(pellesEducations2)).presentation(pellesPresentation2).skills(new HashSet(pellesSkills2)).assignments(new HashSet(pellesAssignments2)).build();

        resumeRepository.save(pelleResume2);


    }

    private  void verifyData() {
        Iterable<Resume> found = resumeRepository.findAll();
        found.forEach(e->log.info(e.toString()));

    }
    private  void storeResumesPerson2() {
        // Kalle
        Person kalle = Person.builder().firstName("Kalle").lastName("Persson").contact(Contact.builder().phone("999").build()).build();
        personRepository.save(kalle);

        List<Education> kallesEducations = Arrays.asList(
                Education.builder().fromYear(1990).toYear(1999).description("matlagningkurs").build(),
                Education.builder().fromYear(2000).toYear(2010).description("bakade bullar").build()
        );
        List<Assignment> kallesAssignments = Arrays.asList(
                Assignment.builder().shortDescription("Kock").longDescription("Lagade mat").fromYear(1970).toYear(1977).employer("Brittas").build(),
                Assignment.builder().shortDescription("Bagare").longDescription("bakade bullar").fromYear(1990).toYear(2000).employer("Annas kafe").build()
        );
        Presentation kallesPresentation = new Presentation("trevlig kock", "Mycket bra kock");
        List<Skill> kallesSkills = Arrays.asList(
                new Skill("Java", Grade.AUTHORITY),
                new Skill("Moccha", Grade.EXPERIENCED)
        );
        Resume kallesResume1 = Resume.builder().title("Kalles CV").person(kalle).educations(new HashSet(kallesEducations)).presentation(kallesPresentation).skills(new HashSet(kallesSkills)).assignments(new HashSet(kallesAssignments)).build();

        resumeRepository.save(kallesResume1);
    }
}
