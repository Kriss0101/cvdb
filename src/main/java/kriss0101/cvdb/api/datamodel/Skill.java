package kriss0101.cvdb.api.datamodel;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    public Skill(String description, Grade grade) {
        this.description = description;
        this.grade = grade;
    }
}
