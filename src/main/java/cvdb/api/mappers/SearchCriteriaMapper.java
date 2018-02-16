package cvdb.api.mappers;

import cvdb.api.commands.SearchCriteriaDTO;
import cvdb.api.datamodel.SearchCriteria;
import org.mapstruct.Mapper;

@Mapper
public interface SearchCriteriaMapper {



    SearchCriteria searchCriteriaDTOToSearchCriteria(SearchCriteriaDTO dto);
    SearchCriteriaDTO searchCriteriaToSearchCriteriaDTO(SearchCriteria criteria);
}
