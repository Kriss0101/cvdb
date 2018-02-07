package kriss0101.cvdb.api.mappers;


import kriss0101.cvdb.api.commands.SkillDTO;
import kriss0101.cvdb.api.datamodel.Grade;
import kriss0101.cvdb.api.datamodel.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    default Skill SkillDTOToSkill(SkillDTO dto) {

        Skill skill  = new Skill(dto.getDescription(), Grade.valueOfName(dto.getGrade()));
        return skill;

    }

    default SkillDTO SkillToSkillDTO(Skill skill) {

        SkillDTO dto = new SkillDTO();
        dto.setDescription(skill.getDescription());
        dto.setGrade(skill.getGrade().getName());
        return dto;
    }

}
