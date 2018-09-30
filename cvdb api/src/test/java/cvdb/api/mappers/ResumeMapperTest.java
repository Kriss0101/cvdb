package cvdb.api.mappers;

import cvdb.api.mappers.EducationMapper;
import cvdb.api.mappers.PersonMapper;
import cvdb.api.mappers.ResumeMapper;
import cvdb.api.commands.ResumeDTO;
import cvdb.api.datamodel.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
@Ignore // Causing clash of resumemapper instances... 
public class ResumeMapperTest {

	
	private ResumeMapper mapper;

	@Test
	public void testResumeToDTO() {
        // Given
		Resume r1 = getTestResume();

	    // When
	    ResumeDTO r1DTO = mapper.resumeToResumeDTO(r1);

        // Then
        assertThat(r1DTO.getId()).isEqualTo(r1.getId());
        assertThat(r1DTO.getTitle()).isEqualTo(r1.getTitle());
        assertThat(r1DTO.getEducations().size()).isEqualTo(r1.getEducations().size());
        assertThat(r1DTO.getAssignments().size()).isEqualTo(r1.getAssignments().size());
        assertThat(r1DTO.getPerson().getFirstName()).isEqualTo(r1.getPerson().getFirstName());

	}
    @Test
    public void testResumeDTOToResume() {
        // Given
        Resume r1 = getTestResume();

        // When
	    ResumeDTO r1DTO = mapper.resumeToResumeDTO(r1);
        Resume resume = mapper.resumeDTOToResume(r1DTO);

        // Then
        assertThat(resume.getId()).isEqualTo(r1DTO.getId());
        assertThat(resume.getTitle()).isEqualTo(r1DTO.getTitle());
        assertThat(resume.getEducations().size()).isEqualTo(r1DTO.getEducations().size());
        assertThat(resume.getAssignments().size()).isEqualTo(r1DTO.getAssignments().size());
        assertThat(resume.getPerson().getFirstName()).isEqualTo(r1DTO.getPerson().getFirstName());

    }

	private Resume getTestResume() {
        // Given
        Person p1 = Person.builder().id(1L).firstName("Pelle").lastName("Persson").build();

         Resume r1 = Resume.builder()
                .person(p1)
                .id(1L)
                .presentation(new Presentation("Very skilled developer", "I am a very very good developer"))
                 .educations(Sets.newSet(Education.builder().id(1L).build()))
                 .assignments(Sets.newSet(Assignment.builder().id(1L).build()))
                .skills(Sets.newSet(new Skill("java", Grade.EXPERIENCED))).build();

         return r1;
    }


//    @Bean
//    public PersonMapper getPersonMapper() {
//        return Mappers.getMapper(PersonMapper.class);
//    }
//    @Bean
//    public EducationMapper getEducationMapper() {
//        return Mappers.getMapper(EducationMapper.class);
//    }
//
//    @Bean
//    public ResumeMapper getResumeMapper() {
//        return Mappers.getMapper(ResumeMapper.class);
//    }

}
