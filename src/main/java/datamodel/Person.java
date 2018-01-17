package datamodel;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
