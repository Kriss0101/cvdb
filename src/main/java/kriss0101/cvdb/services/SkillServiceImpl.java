package kriss0101.cvdb.services;

import kriss0101.cvdb.datamodel.Skill;
import kriss0101.cvdb.repositories.SkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository repository;

    public SkillServiceImpl(SkillRepository repo) {
        this.repository = repo;
    }

    @Override
    public List<Skill> getSkills() {
        ArrayList<Skill> skills = new ArrayList<Skill>();
        repository.findAll().spliterator().forEachRemaining(skills::add);
        return skills;
    }

    @Override
    public Optional<Skill> getSkillById(Long id) {
        return Optional.of(repository.findOne(id));
    }
}