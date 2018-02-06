package kriss0101.cvdb.api.repositories;

import kriss0101.cvdb.api.datamodel.Resume;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  ResumeRepository  extends CrudRepository<Resume, Long>{

    @Query("SELECT DISTINCT(r) FROM Resume r INNER JOIN r.person p INNER JOIN r.assignments a INNER JOIN  r.educations e INNER JOIN r.skills s"
            + " WHERE "
            + " (:firstName = '' or :firstName IS NULL or LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')))"
            + " AND (:lastName = '' OR :lastName IS NULL OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :lastName, '%')))"

            + " AND (:freeText = '' OR :freeText IS NULL "
            + "     OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :freeText, '%')) "
            + "     OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :freeText, '%')) "
            + "     OR LOWER(r.presentation.shortDescription) LIKE LOWER(CONCAT('%', :freeText, '%')) "
            + "     OR LOWER(r.presentation.longDescription) LIKE LOWER(CONCAT('%', :freeText, '%'))"
            + "     OR LOWER(a.employer) LIKE LOWER(CONCAT('%', :freeText, '%'))"
            + "     OR LOWER(a.shortDescription) LIKE LOWER(CONCAT('%', :freeText, '%'))"
            + "     OR LOWER(a.longDescription) LIKE LOWER(CONCAT('%', :freeText, '%'))"
            + "     OR LOWER(e.description) LIKE LOWER(CONCAT('%', :freeText, '%'))"
            + "     OR LOWER(s.description) LIKE LOWER(CONCAT('%', :freeText, '%'))"
            + ")"
    )
    List<Resume> findByFirstNameInOrLastNameInOrFreeTextIn(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("freeText") String freeText);





}
