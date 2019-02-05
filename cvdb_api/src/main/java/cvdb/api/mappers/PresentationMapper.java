package cvdb.api.mappers;

import cvdb.api.commands.PresentationDTO;
import cvdb.api.domain.Presentation;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface PresentationMapper {



     Presentation presentationDTOTOPresentation(PresentationDTO dto);
     PresentationDTO presentationTOPresentationDTO(Presentation dto);

}
