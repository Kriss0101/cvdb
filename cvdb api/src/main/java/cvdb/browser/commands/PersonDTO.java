package cvdb.browser.commands;

import lombok.Data;

@Data
public class PersonDTO {

    private Long id;

    private String firstName;
    private String lastName;

    private ContactDTO contact;

}
