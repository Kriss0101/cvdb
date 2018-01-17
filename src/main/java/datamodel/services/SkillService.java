package datamodel.services;

import datamodel.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {

    List<Skill> getSkills();
    Optional<Skill> getSkillById(Long id);
}
