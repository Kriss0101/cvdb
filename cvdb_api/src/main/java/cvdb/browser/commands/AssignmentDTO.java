package cvdb.browser.commands;

import lombok.Data;

@Data
public class AssignmentDTO {

    private String shortDescription;

    private String longDescription;

    private Integer fromYear;
    private Integer toYear;

    private String employer;
}
