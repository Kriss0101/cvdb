package kriss0101.cvdb.datamodel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortDescription;

    @Lob
    private String longDescription;
    private Integer fromYear;
    private Integer toYear;
    private String employer;

}
