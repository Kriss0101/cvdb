package cvdb.api.mappers;

import cvdb.api.commands.SkillDTO;
import cvdb.api.datamodel.Grade;
import cvdb.api.datamodel.Skill;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class SkillMapperTest {

    @Test
    public void skillDTOToSkill() {
        // Given
        SkillDTO dto = new SkillDTO();
        dto.setDescription("java");
        dto.setGrade(Grade.EXPERIENCED.getName());

        // When
        SkillMapper mapper  = Mappers.getMapper(SkillMapper.class);
        Skill skill = mapper.SkillDTOToSkill(dto);

        // Then
        assertThat(skill.getDescription()).isEqualTo("java");
        assertThat(skill.getGrade()).isEqualTo(Grade.EXPERIENCED);

    }

    @Test
    public void skillToSkillDTO() {
        // Given
        Skill skill = new Skill();
        skill.setDescription("java");
        skill.setGrade(Grade.EXPERIENCED);

        // When
        SkillMapper mapper = Mappers.getMapper(SkillMapper.class);
        SkillDTO skillDTO =mapper.SkillToSkillDTO(skill);

        // Then
        assertThat(skillDTO.getDescription()).isEqualTo("java");
        assertThat(skillDTO.getGrade()).isEqualTo(Grade.EXPERIENCED.getName());

    }
}
