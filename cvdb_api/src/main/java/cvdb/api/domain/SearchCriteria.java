package cvdb.api.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

    private String firstName;
    private String lastName;
    private String freeText;

    public boolean isEmpty() {
        return fieldsEmpty(firstName,lastName,freeText);
    }

    private boolean fieldsEmpty(String...fields) {
        boolean allFieldsEmpty = true;
        for (String field : fields) {
            allFieldsEmpty &=field == null || field.trim().isEmpty();
        }
        return allFieldsEmpty;
    }
}
