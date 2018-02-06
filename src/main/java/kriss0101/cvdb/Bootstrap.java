package kriss0101.cvdb;

import kriss0101.cvdb.api.repositories.PersonRepository;
import kriss0101.cvdb.api.repositories.ResumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    PersonRepository personRepository;

    private Logger logger = LoggerFactory.getLogger(Bootstrap.class.getName());

    @Override
    public void run(String... strings) throws Exception {

        logger.info("******** Bootstraping data ************");
        ResumesTestFixture fixture = new ResumesTestFixture(resumeRepository, personRepository);
        fixture.storeResumeTestData();

        resumeRepository.findAll().forEach(e -> logger.debug(e.toString()));
    }

//
//    private class ResumesTestFixture {
//
//
//        private final ResumeRepository resumeRepository;
//
//
//        public ResumesTestFixture(ResumeRepository resumeRepository) {
//            this.resumeRepository = resumeRepository;
//        }
//
//        public  void storeResumeTestData() {
//
//            storeResumesPerson1();
//            storeResumesPerson2();
//
//            printData();
//        }
//        private  void storeResumesPerson1() {
//            // Pelle
//            Person pelle = Person.builder().firstName("Pelle").lastName("Persson").contact(Contact.builder().phone("999").build()).build();
//            pelle = personRepository.save(pelle);
//
//            List<Education> pellesEducations = Arrays.asList(
//                    Education.builder().fromYear(1990).toYear(1999).description("javakurs").build(),
//                    Education.builder().fromYear(2000).toYear(2010).description("c++ in action").build()
//            );
//
//            List<Assignment> pellesAssignments = Arrays.asList(
//                    Assignment.builder().shortDescription("Matematiker").longDescription("gjorde beräkningar på balkar").fromYear(1970).toYear(1977).employer("Volvo").build(),
//                    Assignment.builder().shortDescription("programmerare").longDescription("gjorde en hemsida").fromYear(1990).toYear(2000).employer("webproffsen").build()
//            );
//            Presentation pellesPresentation = new Presentation("Briljant programmerare", "Mycket trevlig programmerare");
//            List<Skill> pellesSkills = Arrays.asList(
//                    new Skill("Java", Grade.AUTHORITY),
//                    new Skill("Koka kaffe", Grade.EXPERIENCED)
//            );
//            Resume pelleResume1 = Resume.builder().title("Pelles första CV").person(pelle).educations(new HashSet(pellesEducations)).presentation(pellesPresentation).skills(new HashSet(pellesSkills)).assignments(new HashSet(pellesAssignments)).build();
//
//            resumeRepository.save(pelleResume1);
//
//            logger.info("ID of saved pelle user after saving resume 1: id = " + pelle.getId());
//            logger.info("ID of saved resume  after saving resume 1: id = " + pelleResume1.getId());
//
//            // Second resume
//            List<Education> pellesEducations2 = Arrays.asList(
//                    Education.builder().fromYear(1990).toYear(1999).description("javakurs2").build(),
//                    Education.builder().fromYear(2000).toYear(2010).description("c++ in action2").build()
//            );
//
//            List<Assignment> pellesAssignments2 = Arrays.asList(
//                    Assignment.builder().shortDescription("Matematiker2").longDescription("gjorde beräkningar på balkar2").fromYear(1970).toYear(1977).employer("Volvo2").build(),
//                    Assignment.builder().shortDescription("programmerare2").longDescription("gjorde en hemsida2").fromYear(1990).toYear(2000).employer("webproffsen2").build()
//            );
//            Presentation pellesPresentation2 = new Presentation("Briljant programmerare2", "Mycket trevlig programmerare2");
//            List<Skill> pellesSkills2 = Arrays.asList(
//                    new Skill("Java", Grade.AUTHORITY),
//                    new Skill("Koka kaffe", Grade.EXPERIENCED)
//            );
//            //Assert.assertTrue(pellesAssignments.get(0).getId() != null);
//            Resume pelleResume2 = Resume.builder().title("Pelles andra CV").person(pelle).educations(new HashSet(pellesEducations2)).presentation(pellesPresentation2).skills(new HashSet(pellesSkills2)).assignments(new HashSet(pellesAssignments2)).build();
//
//            resumeRepository.save(pelleResume2);
//
//        }
//
//        private  void printData() {
//            Iterable<Resume> found = resumeRepository.findAll();
//            found.forEach(System.out::println);
//
//        }
//        private  void storeResumesPerson2() {
//            // Kalle
//            Person kalle = Person.builder().firstName("Kalle").lastName("Persson").contact(Contact.builder().phone("999").build()).build();
//            kalle=personRepository.save(kalle);
//
//            List<Education> kallesEducations = Arrays.asList(
//                    Education.builder().fromYear(1990).toYear(1999).description("matlagningkurs").build(),
//                    Education.builder().fromYear(2000).toYear(2010).description("bakade bullar").build()
//            );
//            List<Assignment> kallesAssignments = Arrays.asList(
//                    Assignment.builder().shortDescription("Kock").longDescription("Lagade mat").fromYear(1970).toYear(1977).employer("Brittas").build(),
//                    Assignment.builder().shortDescription("Bagare").longDescription("bakade bullar").fromYear(1990).toYear(2000).employer("Annas kafe").build()
//            );
//            Presentation kallesPresentation = new Presentation("trevlig kock", "Mycket bra kock");
//            List<Skill> kallesSkills = Arrays.asList(
//                    new Skill("Java", Grade.AUTHORITY),
//                    new Skill("Moccha", Grade.EXPERIENCED)
//            );
//            Resume kallesResume1 = Resume.builder().title("Kalles CV").person(kalle).educations(new HashSet(kallesEducations)).presentation(kallesPresentation).skills(new HashSet(kallesSkills)).assignments(new HashSet(kallesAssignments)).build();
//
//            resumeRepository.save(kallesResume1);
//        }
//    }
//
//

}
