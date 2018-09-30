package cvdb.browser.commands;

import lombok.Data;

@Data
public class SearchCriteriaDTO {

    private String firstName;
    private String lastName;
    private String freeText;
}
