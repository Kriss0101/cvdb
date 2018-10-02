package cvdb.api.mappers;

import cvdb.api.commands.SearchCriteriaDTO;
import cvdb.api.datamodel.SearchCriteria;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchCriteriaMapperTest {

    private SearchCriteriaMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = Mappers.getMapper(SearchCriteriaMapper.class);

    }

    // This test helps remember to update the tests in this class to acknowledge new fields added to the search criteria
    @Test
    public void searchCriteriaDTO_ExpectedNumberOfFields() {

        // Given
        SearchCriteriaDTO dto = new SearchCriteriaDTO();
        int nFieldsExpected = 3;

        // When, Then
        assertThat(dto.getClass().getDeclaredFields().length).isEqualTo(nFieldsExpected);

    }

    @Test
    public void searchCriteriaDTOToSearchCriteria() {

        // Given
        SearchCriteriaDTO dto = new SearchCriteriaDTO();
        dto.setFirstName("first");
        dto.setLastName("last");
        dto.setFreeText("text");

        // When
        SearchCriteria crit = mapper.searchCriteriaDTOToSearchCriteria(dto);

        // Then
        assertThat(crit.getFirstName()).isEqualTo(dto.getFirstName());
        assertThat(crit.getLastName()).isEqualTo(dto.getLastName());
        assertThat(crit.getFreeText()).isEqualTo(dto.getFreeText());

    }

    @Test
    public void searchCriteriaToSearchCriteriaDTO() {
        // Given
        SearchCriteria crit = SearchCriteria.builder().firstName("first").lastName("last").freeText("free").build();

        // When
        SearchCriteriaDTO dto = mapper.searchCriteriaToSearchCriteriaDTO(crit);
        // Then
        assertThat(dto.getFirstName()).isEqualTo(crit.getFirstName());
        assertThat(dto.getLastName()).isEqualTo(crit.getLastName());
        assertThat(dto.getFreeText()).isEqualTo(crit.getFreeText());
    }
}