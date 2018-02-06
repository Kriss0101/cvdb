package kriss0101.cvdb.api.mappers;

import kriss0101.cvdb.api.commands.SearchCriteriaDTO;
import kriss0101.cvdb.api.datamodel.SearchCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SearchCriteriaMapper {

    SearchCriteriaMapper INSTANCE = Mappers.getMapper(SearchCriteriaMapper.class);

    SearchCriteria searchCriteriaDTOToSearchCriteria(SearchCriteriaDTO dto);
    SearchCriteriaDTO searchCriteriaToSearchCriteriaDTO(SearchCriteria criteria);
}
