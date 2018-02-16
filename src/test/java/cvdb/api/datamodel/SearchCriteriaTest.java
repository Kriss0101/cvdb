package cvdb.api.datamodel;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchCriteriaTest {

    @Test
    public void isEmpty_ExpectEmpty() {

        // Given
        SearchCriteria criteria = SearchCriteria.builder().firstName("").lastName("  ").freeText(null).build();

        // When..then
        assertThat(criteria.isEmpty()).isTrue();
    }

    @Test
    public void isEmpty_ExpectNotEmpty() {

    // Given
    SearchCriteria criteria = SearchCriteria.builder().firstName("").lastName("  ").freeText("someconteent").build();

    // When..then
    assertThat(criteria.isEmpty()).isFalse();
}
}