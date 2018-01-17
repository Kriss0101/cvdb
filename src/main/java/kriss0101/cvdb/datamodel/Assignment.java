package kriss0101.cvdb.datamodel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String shortDescription;

    @Lob
    String longDescription;
    Integer fromYear;
    Integer toYear;
    String employer;

}
