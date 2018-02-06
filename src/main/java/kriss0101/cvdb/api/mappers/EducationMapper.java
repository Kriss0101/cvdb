package kriss0101.cvdb.api.mappers;


import kriss0101.cvdb.api.commands.EducationDTO;
import kriss0101.cvdb.api.datamodel.Education;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    Education educationDTOEducation(EducationDTO dto);
    EducationDTO educationToEducationDTO(Education education);

}
