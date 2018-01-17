package datamodel;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String shortDescription;

    @Lob
    String longDescription;
}
