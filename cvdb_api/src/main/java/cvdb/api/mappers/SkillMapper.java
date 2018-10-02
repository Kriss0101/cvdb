package cvdb.api.mappers;


import cvdb.api.commands.SkillDTO;
import cvdb.api.datamodel.Grade;
import cvdb.api.datamodel.Skill;
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
