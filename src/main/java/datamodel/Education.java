package datamodel;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Lob
    String description;

    Integer fromYear;
    Integer toYear;


}
