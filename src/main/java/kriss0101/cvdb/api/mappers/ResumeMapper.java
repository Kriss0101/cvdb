package kriss0101.cvdb.api.mappers;

import kriss0101.cvdb.api.commands.ResumeDTO;
import kriss0101.cvdb.api.datamodel.Resume;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(uses={ContactMapper.class, EducationMapper.class, PersonMapper.class, PresentationMapper.class, SkillMapper.class}, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface  ResumeMapper {

    ResumeDTO resumeToResumeDTO(Resume resume);
    Resume resumeDTOToResume(ResumeDTO dto);


}
