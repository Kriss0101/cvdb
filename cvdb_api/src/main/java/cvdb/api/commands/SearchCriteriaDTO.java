package cvdb.api.commands;

import lombok.Data;

@Data
public class SearchCriteriaDTO {

    private String firstName;
    private String lastName;
    private String freeText;

}
