package datamodel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob
    String description;

    @OneToMany(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    SkillLevel level;

}
