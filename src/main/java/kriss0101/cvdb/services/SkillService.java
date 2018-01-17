package kriss0101.cvdb.services;

import kriss0101.cvdb.datamodel.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {

    List<Skill> getSkills();
    Optional<Skill> getSkillById(Long id);
}
