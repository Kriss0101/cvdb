package kriss0101.cvdb.api.mappers;


import kriss0101.cvdb.api.commands.EducationDTO;
import kriss0101.cvdb.api.datamodel.Education;
import org.mapstruct.Mapper;

@Mapper
public interface EducationMapper {



    Education educationDTOEducation(EducationDTO dto);
    EducationDTO educationToEducationDTO(Education education);

}
