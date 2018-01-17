package kriss0101.cvdb.datamodel;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortDescription;

    @Lob
    private String longDescription;

    public Presentation(String shortDescription, String longDescription) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }
}
