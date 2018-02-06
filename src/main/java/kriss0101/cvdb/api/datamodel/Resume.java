package kriss0101.cvdb.api.datamodel;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;

    private String title;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Presentation presentation;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="resume_id")
    private Set<Skill> skills;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="resume_id")
    private Set<Assignment> assignments;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="resume_id")
    private Set<Education> educations;


}
