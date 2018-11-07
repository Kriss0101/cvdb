package cvdb.api.mappers;


import cvdb.api.commands.EducationDTO;
import cvdb.api.datamodel.Education;
import org.mapstruct.Mapper;

@Mapper
public interface EducationMapper {



    Education educationDTOEducation(EducationDTO dto);
    EducationDTO educationToEducationDTO(Education education);

}
