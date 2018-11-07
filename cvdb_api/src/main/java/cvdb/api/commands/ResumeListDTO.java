package cvdb.api.commands;

import cvdb.api.datamodel.Resume;
import lombok.Data;

import java.util.List;

@Data
public class ResumeListDTO {

    List<Resume> resumes;
}
