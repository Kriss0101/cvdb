package cvdb.api.commands;

import lombok.Data;

import java.util.List;

import cvdb.api.domain.Resume;

@Data
public class ResumeListDTO {

    List<Resume> resumes;
}
