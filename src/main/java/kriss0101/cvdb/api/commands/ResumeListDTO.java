package kriss0101.cvdb.api.commands;

import kriss0101.cvdb.api.datamodel.Resume;
import lombok.Data;

import java.util.List;

@Data
public class ResumeListDTO {

    List<Resume> resumes;
}
