package cvdb.api.mappers;

import cvdb.api.commands.SearchCriteriaDTO;
import cvdb.api.datamodel.SearchCriteria;
import org.mapstruct.Mapper;

@Mapper
public interface SearchCriteriaMapper {

    default SearchCriteria searchCriteriaDTOToSearchCriteria(SearchCriteriaDTO dto) {
        SearchCriteria criteria = SearchCriteria.builder()
                .firstName(dto.getFirstName().trim())
                .lastName(dto.getLastName().trim())
                .freeText(dto.getFreeText().trim()).build();

        return criteria;

    }
    SearchCriteriaDTO searchCriteriaToSearchCriteriaDTO(SearchCriteria criteria);
}
