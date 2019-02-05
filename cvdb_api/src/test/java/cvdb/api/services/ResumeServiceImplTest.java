package cvdb.api.services;

import cvdb.api.services.ResumeService;
import cvdb.api.services.ResumeServiceImpl;
import cvdb.api.domain.*;
import cvdb.api.repositories.ResumeRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ResumeServiceImplTest {

    @Mock
    ResumeRepository resumeRepository;

    ResumeService resumeService;
    private List<Resume> resumesFixture;
    private Resume resumeFixture;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        resumesFixture = getResumeCollectionFixture();

        resumeFixture = resumesFixture.get(0);
        resumeService = new ResumeServiceImpl(resumeRepository);
    }

    @Test
    public void getResumes() {

        // Given
        when(resumeRepository.findAll()).thenReturn(resumesFixture);

        // When
        List<Resume> resumes = resumeService.getResumes();

        // Then
        assertThat(resumes).hasSize(2);

    }


    @Test
    public void getById() {
        // Given
        when(resumeRepository.findById(anyLong())).thenReturn(Optional.of(resumeFixture));
        Long idOfFixture = resumeFixture.getId();

        // When
        Optional<Resume> resume = resumeService.getById(idOfFixture);

        // Then
        assertThat(resume.isPresent()).isTrue();
        assertThat(resume.get().getId()).isEqualTo(idOfFixture);
    }
    @Test
    public void searchWithEmptyCroteroa_expectTwoResumes() {
        // Given
        when(resumeRepository.findByFirstNameInOrLastNameInOrFreeTextIn(anyString(),anyString(),anyString())).thenReturn(resumesFixture);

        // When
        SearchCriteria criteria = SearchCriteria.builder()
                .firstName("")
                .lastName("")
                .freeText("")
                .build();
        List<Resume> searchResult = resumeService.search(criteria);
        // Then
        assertThat(searchResult).hasSize(2);
    }

//    @Test
//    public void searchByFirstAndLastName_expectOneResumes() {
//        // Given
//        when(resumeRepository.findAll()).thenReturn(resumesFixture);
//
//        // When
//        SearchCriteria criteria = SearchCriteria.builder()
//                .firstName("pelle")
//                .lastName("Persson")
//                .freeText("")
//                .build();
//        List<Resume> searchResult = resumeService.search(criteria);
//        // Then
//        assertThat(searchResult).hasSize(1);
//    }
//    @Test
//    public void searchByLastName_expectTwoResumes() {
//        // Given
//        when(resumeRepository.findAll()).thenReturn(resumesFixture);
//
//        // When
//        SearchCriteria criteria = SearchCriteria.builder()
//                .firstName("")
//                .lastName("Persson")
//                .freeText("")
//                .build();
//        List<Resume> searchResult = resumeService.search(criteria);
//        // Then
//        assertThat(searchResult).hasSize(2);
//    }
//    @Test
//    public void searchByShortDescription_expectOneResume() {
//        // Given
//        when(resumeRepository.findAll()).thenReturn(resumesFixture);
//
//        // When
//        SearchCriteria criteria = SearchCriteria.builder()
//                .firstName("")
//                .lastName("")
//                .freeText("c++")
//                .build();
//        List<Resume> searchResult = resumeService.search(criteria);
//        // Then
//        assertThat(searchResult).hasSize(1);
//    }
//    @Test
//    public void searchByLongDescription_expectOneResume() {
//        // Given
//        when(resumeRepository.findAll()).thenReturn(resumesFixture);
//
//        // When
//        SearchCriteria criteria = SearchCriteria.builder()
//                .firstName("")
//                .lastName("")
//                .freeText("good java developer")
//                .build();
//        List<Resume> searchResult = resumeService.search(criteria);
//        // Then
//        assertThat(searchResult).hasSize(1);
//    }
//    @Test
//    public void searchByEducation_expectOneResume_1() {
//        // Given
//        when(resumeRepository.findAll()).thenReturn(resumesFixture);
//
//        // When
//        SearchCriteria criteria = SearchCriteria.builder()
//                .firstName("")
//                .lastName("")
//                .freeText("Beräkningsteknik")
//                .build();
//        List<Resume> searchResult = resumeService.search(criteria);
//        // Then
//        assertThat(searchResult.size()).isEqualTo(1);
//    }
//    @Test
//    public void searchByAssignment_expectTwoResumes() {
//        // Given
//        when(resumeRepository.findAll()).thenReturn(resumesFixture);
//
//        // When
//        SearchCriteria criteria = SearchCriteria.builder()
//                .firstName("")
//                .lastName("")
//                .freeText("good worker")
//                .build();
//        List<Resume> searchResult = resumeService.search(criteria);
//        // Then
//        assertThat(searchResult.size()).isEqualTo(2);
//    }
//    @Test
//    public void searchByAssignment_expectOneResume() {
//        // Given
//        when(resumeRepository.findAll()).thenReturn(resumesFixture);
//
//        // When
//        SearchCriteria criteria = SearchCriteria.builder()
//                .firstName("")
//                .lastName("")
//                .freeText("nice worker")
//                .build();
//        List<Resume> searchResult = resumeService.search(criteria);
//        // Then
//        assertThat(searchResult.size()).isEqualTo(1);
//    }
//    @Test
//    public void searchByEducation_expectOneResume_2() {
//        // Given
//        when(resumeRepository.findAll()).thenReturn(resumesFixture);
//
//        // When
//        SearchCriteria criteria = SearchCriteria.builder()
//                .firstName("")
//                .lastName("")
//                .freeText("teknik")
//                .build();
//        List<Resume> searchResult = resumeService.search(criteria);
//        // Then
//        assertThat(searchResult).hasSize(2);
//    }
//    @Test
//    public void searchBySomeDummyThings_expectNoResume() {
//        // Given
//        when(resumeRepository.findAll()).thenReturn(resumesFixture);
//
//        // When
//        SearchCriteria criteria = SearchCriteria.builder()
//                .firstName("no name")
//                .lastName("nameson")
//                .freeText("korvförsäljare")
//                .build();
//        List<Resume> searchResult = resumeService.search(criteria);
//        // Then
//        assertThat(searchResult).hasSize(0);
//    }
    @Test
    public void save() {
        // given
        Resume resumeToSave = resumeFixture;

        // when
        Resume savedResume = resumeService.save(resumeToSave);

        // then
        ArgumentCaptor<Resume> argCapture = ArgumentCaptor.forClass(Resume.class);
        verify(resumeRepository, times(1)).save(argCapture.capture());
        assertThat(argCapture.getValue()).isEqualTo(resumeToSave);
    }


    private final List<Resume> getResumeCollectionFixture() {


        Person pelle = Person.builder().firstName("Pelle").lastName("Persson")
                .contact(Contact.builder().adress("vägen 2").email("pelle@gmail.com").phone("234234").build())
                .build();

        Resume r1 = Resume.builder().id(1L).skills(Sets.newSet(new Skill("java", Grade.EXPERIENCED)))
                .presentation(new Presentation("Java coder","I am a very good java developer"))
                .educations(Sets.newSet(Education.builder().fromYear(2014).toYear(2017).description("Beräkningsteknik 120p").build()))
                .assignments(Sets.newSet(Assignment.builder().employer("the company").fromYear(2000).toYear(2010).shortDescription("good worker").longDescription("a good worker").build()))
                .person(pelle)
                .id(2L).build();

        Person kalle = Person.builder().firstName("Kalle").lastName("Persson")
                .contact(Contact.builder().adress("vägen 3").email("kalle@gmail.com").phone("234234").build())
                .build();

        Resume r2 = Resume.builder().id(2L).skills(Sets.newSet(new Skill("c++",Grade.AUTHORITY)))
                .presentation(new Presentation("C++ coder","I am a good c++ developer"))
                .educations(Sets.newSet(Education.builder().fromYear(2014).toYear(2017).description("Datateknik 180p").build()))
                .assignments(Sets.newSet(Assignment.builder().employer("the company").fromYear(2000).toYear(2010).shortDescription("good worker").longDescription("a nice worker").build()))
                .person(kalle)
                .id(2L).build();
        return Arrays.asList(r1,r2);

    }
}