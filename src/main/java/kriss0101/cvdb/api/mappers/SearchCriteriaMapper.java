package kriss0101.cvdb.api.mappers;

import kriss0101.cvdb.api.commands.SearchCriteriaDTO;
import kriss0101.cvdb.api.datamodel.SearchCriteria;
import org.mapstruct.Mapper;

@Mapper
public interface SearchCriteriaMapper {



    SearchCriteria searchCriteriaDTOToSearchCriteria(SearchCriteriaDTO dto);
    SearchCriteriaDTO searchCriteriaToSearchCriteriaDTO(SearchCriteria criteria);
}
