package cvdb;

import java.util.Set;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cvdb.api.domain.*;
import cvdb.api.repositories.PersonRepository;
import cvdb.api.repositories.ResumeRepository;
import cvdb.api.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Test data set. Quite small for sake of first implementation.
 * 
 * @author Kristofer
 *
 */
@Slf4j
@EnableTransactionManagement
public class ResumesTestFixture {

	private final ResumeRepository resumeRepository;
	private final PersonRepository personRepository;

	public ResumesTestFixture(ResumeRepository resumeRepository, PersonRepository personRepository) {
		this.resumeRepository = resumeRepository;
		this.personRepository = personRepository;
	}

	@Transactional
	public void storeResumeTestData() {

		storeResumesPerson1();
		storeResumesPerson2();
		storeResumesPerson3();

		outputResumes();
	}

	public void storeResumesPerson1() {

		// Person 1
		Person pelle = Person.builder().firstName("Nils").lastName("Karlsson").contact(
				Contact.builder().adress("Vägen 1").email("nils.karlsson@hotmail.com").phone("999888777").build())
				.build();

		personRepository.save(pelle);

		saveResume1(pelle);

		saveResume2(pelle);

	}

	private void saveResume2(Person pelle) {
		Set<Assignment> pellesAssignments2 = CollectionUtil.asLinkedHashSet(
				Assignment.builder().shortDescription("Java developer").longDescription("Build a website")
						.fromYear(1990).toYear(2000).employer("The Web bros").build(),
				Assignment.builder().shortDescription("C++ developer")
						.longDescription("Developed algorithms for a robot").fromYear(2001).toYear(2006)
						.employer("NASA").build());

		// His resumes
		Set<Education> pellesEducations = CollectionUtil.asLinkedHashSet(
				Education.builder().fromYear(1990).toYear(1999).description("Java intermediate course").build(),
				Education.builder().fromYear(2000).toYear(2010).description("c++ in action").build());

		Presentation pellesPresentation2 = new Presentation("Brilliant programmer",
				"I'm a very skilled developer and likes to work in advanced domains. I also like coffee.");
		Set<Skill> pellesSkills2 = CollectionUtil.asLinkedHashSet(new Skill("Java", Grade.EXPERIENCED),
				new Skill("C++", Grade.EXPERIENCED), new Skill("Drink coffee", Grade.AUTHORITY));

		Resume pelleResume2 = Resume.builder().title("Proffessional coder").person(pelle).educations(pellesEducations)
				.presentation(pellesPresentation2).skills(pellesSkills2).assignments(pellesAssignments2).build();

		resumeRepository.save(pelleResume2);
	}

	private void saveResume1(Person pelle) {
		Set<Assignment> pellesAssignments = CollectionUtil.asLinkedHashSet(
				Assignment.builder().shortDescription("Applied mathematician")
						.longDescription("Optimized a system for an autonomous vehicle").fromYear(2014).toYear(2016)
						.employer("Volvo").build(),
				Assignment.builder().shortDescription("Researcher")
						.longDescription("Did research in computational statistics").fromYear(2017).toYear(2018)
						.employer("Karolinska Institutet").build());

		// His resumes
		Set<Education> pellesEducations = CollectionUtil.asLinkedHashSet(
				Education.builder().fromYear(1990).toYear(1999).description("Java intermediate course").build(),
				Education.builder().fromYear(2000).toYear(2010).description("c++ in action").build());

		Presentation pellesPresentation = new Presentation("Brilliant mathematician",
				"I like to solve your problems, please hire me.");
		Set<Skill> pellesSkills = CollectionUtil.asLinkedHashSet(new Skill("Java", Grade.AUTHORITY),
				new Skill("C++", Grade.EXPERIENCED), new Skill("Computational Statistics", Grade.EXPERIENCED));
		Resume pelleResume1 = Resume.builder().title("Mathematician").person(pelle).educations(pellesEducations)
				.presentation(pellesPresentation).skills(pellesSkills).assignments(pellesAssignments).build();
		resumeRepository.save(pelleResume1);
	}

	private void storeResumesPerson2() {

		// Person
		Person kalle = Person.builder().firstName("Kalle").lastName("Kodare")
				.contact(Contact.builder().phone("999111222").build()).build();
		personRepository.save(kalle);

		// His resumes
		Set<Education> kallesEducations = CollectionUtil.asLinkedHashSet(
				Education.builder().fromYear(2001).toYear(2005).description("Elektroteknik på KTH").build(),
				Education.builder().fromYear(2016).toYear(2018).description("Microservices").build());
		Set<Assignment> kallesAssignments = CollectionUtil.asLinkedHashSet(
				Assignment.builder().shortDescription("Webdeveloper").longDescription("I build a website.")
						.fromYear(2010).toYear(2015).employer("The web bros").build(),
				Assignment.builder().shortDescription("Java developer").longDescription(
						"I rebuild their monolithic system into microservice architecture. Now it runs smooth :)")
						.fromYear(2018).toYear(2018).employer("Mono systems AB").build());
		Presentation kallesPresentation = new Presentation("Microservice Professional",
				"Consultant with great knowledge in Domain Driven Design, TDD and experience in devloping Microservices");
		Set<Skill> kallesSkills = CollectionUtil.asLinkedHashSet(new Skill("Java SE 8", Grade.AUTHORITY),
				new Skill("Spring Boot 2", Grade.AUTHORITY), new Skill("Spring Framework 5", Grade.AUTHORITY),
				new Skill("DevOps", Grade.EXPERIENCED), new Skill("Scrum", Grade.AUTHORITY),
				new Skill("DDD", Grade.AUTHORITY)

		);
		Resume resume1 = Resume.builder().title("Senior developer with great charisma").person(kalle)
				.educations(kallesEducations).presentation(kallesPresentation).skills(kallesSkills)
				.assignments(kallesAssignments).build();

		resumeRepository.save(resume1);
	}

	private void storeResumesPerson3() {

		// Person
		Person kalle = Person.builder().firstName("Jens").lastName("Joder")
				.contact(Contact.builder().phone("999111111").build()).build();
		personRepository.save(kalle);

		// His resumes
		Set<Education> kallesEducations = CollectionUtil
				.asLinkedHashSet(Education.builder().fromYear(2001).toYear(2005).description("Datateknik").build()

		);
		Set<Assignment> kallesAssignments = CollectionUtil.asLinkedHashSet(Assignment.builder()
				.shortDescription("C programmer").longDescription("I programmed their telecom system.").fromYear(1980)
				.toYear(2015).employer("Telekom AB").build()

		);
		Presentation kallesPresentation = new Presentation("C programmer",
				"I'm a great programmer looking for new exciting assignments.");
		Set<Skill> kallesSkills = CollectionUtil.asLinkedHashSet(new Skill("C", Grade.AUTHORITY),
				new Skill("Bug Driven Development", Grade.AUTHORITY)

		);
		Resume resume1 = Resume.builder().title("C programmer").person(kalle).educations(kallesEducations)
				.presentation(kallesPresentation).skills(kallesSkills).assignments(kallesAssignments).build();

		resumeRepository.save(resume1);
	}

	private void outputResumes() {
		Iterable<Resume> found = resumeRepository.findAll();
		found.forEach(e -> log.info(e.toString()));

	}
}
