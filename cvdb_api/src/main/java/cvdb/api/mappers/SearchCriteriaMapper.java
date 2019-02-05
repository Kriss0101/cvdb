package cvdb.api.mappers;

import org.mapstruct.Mapper;

import cvdb.api.commands.SearchCriteriaDTO;
import cvdb.api.domain.SearchCriteria;

@Mapper
public interface SearchCriteriaMapper {

	// default SearchCriteria searchCriteriaDTOToSearchCriteria(SearchCriteriaDTO
	// dto) {
	// SearchCriteria criteria = SearchCriteria.builder()
	// .firstName(dto.getFirstName().trim())
	// .lastName(dto.getLastName().trim())
	// .freeText(dto.getFreeText().trim()).build();
	//
	// return criteria;
	//
	// }
	SearchCriteria searchCriteriaDTOToSearchCriteria(SearchCriteriaDTO dto);
    SearchCriteriaDTO searchCriteriaToSearchCriteriaDTO(SearchCriteria criteria);
}
