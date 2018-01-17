package kriss0101.cvdb.commands;

import lombok.Data;

@Data
public class PersonDTO {

    private String firstName;
    private String lastName;

    private ContactDTO contactDTO;
    private PresentationDTO presentationDTO;




}
