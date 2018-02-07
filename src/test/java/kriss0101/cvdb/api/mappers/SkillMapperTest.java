package kriss0101.cvdb.api.mappers;

import kriss0101.cvdb.api.commands.SkillDTO;
import kriss0101.cvdb.api.datamodel.Grade;
import kriss0101.cvdb.api.datamodel.Skill;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SkillMapperTest {

    @Test
    public void skillDTOToSkill() {
        // Given
        SkillDTO dto = new SkillDTO();
        dto.setDescription("java");
        dto.setGrade(Grade.EXPERIENCED.getName());

        // When
        SkillMapper mapper  = new SkillMapperImpl();
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
        SkillMapper mapper = new SkillMapperImpl();
        SkillDTO skillDTO =mapper.SkillToSkillDTO(skill);

        // Then
        assertThat(skillDTO.getDescription()).isEqualTo("java");
        assertThat(skillDTO.getGrade()).isEqualTo(Grade.EXPERIENCED.getName());

    }
}
