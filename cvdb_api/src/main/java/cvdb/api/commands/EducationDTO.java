package cvdb.api.commands;

import lombok.Data;

@Data
public class EducationDTO {


    private String description;

    private Integer fromYear;
    private Integer toYear;

}
