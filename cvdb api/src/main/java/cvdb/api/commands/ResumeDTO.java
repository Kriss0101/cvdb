package cvdb.api.commands;

import lombok.Data;

import java.util.List;

@Data
public class ResumeDTO {

    private Long id;

    private PersonDTO person;

    private String title;

    private PresentationDTO presentation;

    private List<SkillDTO> skills;


    private List<AssignmentDTO> assignments;


    private List<EducationDTO> educations;


}
