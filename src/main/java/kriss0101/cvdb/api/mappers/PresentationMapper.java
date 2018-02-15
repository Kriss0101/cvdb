package kriss0101.cvdb.api.mappers;

import kriss0101.cvdb.api.commands.PresentationDTO;
import kriss0101.cvdb.api.datamodel.Presentation;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PresentationMapper {



     Presentation presentationDTOTOPresentation(PresentationDTO dto);
     PresentationDTO presentationTOPresentationDTO(Presentation dto);

}
