package kriss0101.cvdb.datamodel;

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

    public Presentation(String shortDescription, String longDescription) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }
}
