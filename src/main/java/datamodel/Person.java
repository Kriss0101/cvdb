package datamodel;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;
    String lastName;

    @OneToOne(fetch = FetchType.EAGER)
    Contact contact;

    @OneToOne(fetch = FetchType.EAGER)
    Presentation presentation;


}
