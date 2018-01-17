package kriss0101.cvdb.datamodel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SkillGrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private Skill skill;

    @OneToMany(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Grade grade;

}
