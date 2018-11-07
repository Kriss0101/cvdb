package cvdb.api.repositories;

import cvdb.api.repositories.PersonRepository;
import cvdb.api.repositories.ResumeRepository;
import cvdb.api.datamodel.Resume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ResumeRepositoryIT {


    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private PersonRepository personRepository;
    private ResumesTestFixture resumesTestFixture;


    @Before
    @Transactional
    public void setUp() throws Exception {
        resumesTestFixture = new ResumesTestFixture(resumeRepository, personRepository);
        resumesTestFixture.storeResumeTestData();
    }

    @Test
    public void testSearchEmptyArguments_ExpectThreeResumes() {

        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(3);

    }

    @Test
    public void testSearchByPresentation_ExpectTwoResumes() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "Briljant programmerare";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(2);


    }

    @Test
    public void testSearchByFirstName_ExpectTwoResumes() {
        // Given
        String firstName = "Pelle";
        String lastName = "";
        String freeText = "";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(2);


    }

    @Test
    public void testSearchByFirstNamePart_ExpectTwoResumes() {
        // Given
        String firstName = "Pelle";
        String lastName = "";
        String freeText = "";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(2);


    }

    @Test
    public void testSearchByLsatNamePart_ExpectTwoResumes() {
        // Given
        String firstName = "";
        String lastName = "ersson";
        String freeText = "";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(3);


    }

    @Test
    public void testSearchByPresentationShortDescription_ExpectTwoResumes() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "Briljant";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(2);


    }

    @Test
    public void testSearchByPresentationLongDescription_ExpectThreeResumes() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "trevlig";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(3);


    }

    @Test
    public void testSearchByAssignmentShortDescription_ExpectTwoResumes() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "matematiker";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(2);

    }

    @Test
    public void testSearchByAssignmentLongDescription_ExpectOneResume() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "balkar2";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(1);

    }

    @Test
    public void testSearchAssignmentCompany_Expect1Resume() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "Brittas";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(1);
    }

    @Test
    public void testSearchEducationShortDescription_Expect1Resume() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "matlagning";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(1);
    }

    @Test
    public void testSearchSkills_Expect1Resume() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "koka kaffe";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(2);
    }
    @Test
    public void testSearchFreeText_ExpectHitFirstName_Expect2Resumes() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "Pell";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(2);
    }
    @Test
    public void testSearchFreeText_ExpectHitLastname_Expect3Resumes() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "Persson";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(3);
    }
    @Test
    public void testSearchFreeText_ExpectHitBothFirstAndLastname_Expect3Resumes() {
        // Given
        String firstName = "";
        String lastName = "";
        String freeText = "Pe";

        // When
        List<Resume> resumes = resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(firstName, lastName, freeText);

        // Then
        assertThat(resumes).hasSize(3);
    }
}
