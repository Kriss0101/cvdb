package kriss0101.cvdb.api.commands;

import kriss0101.cvdb.api.datamodel.Skill;
import lombok.Data;

import java.util.List;

@Data
public class SkillListDTO {

    private List<Skill> skills;

}
