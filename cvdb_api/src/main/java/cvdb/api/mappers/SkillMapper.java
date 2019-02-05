package cvdb.api.mappers;


import cvdb.api.commands.SkillDTO;
import cvdb.api.domain.Grade;
import cvdb.api.domain.Skill;

import org.mapstruct.Mapper;

@Mapper
public interface SkillMapper {

    default Skill SkillDTOToSkill(SkillDTO dto) {
        return new Skill(dto.getDescription(), Grade.valueOfName(dto.getGrade()));
    }

    default SkillDTO SkillToSkillDTO(Skill skill) {

        SkillDTO dto = new SkillDTO();
        dto.setDescription(skill.getDescription());
        dto.setGrade(skill.getGrade().getName());
        return dto;
    }

}
