package kriss0101.cvdb.api.mappers;

import kriss0101.cvdb.api.commands.PresentationDTO;
import kriss0101.cvdb.api.datamodel.Presentation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//
@Mapper
public interface PresentationMapper {

     PresentationMapper INSTANCE = Mappers.getMapper(PresentationMapper.class);

     Presentation presentationDTOTOPresentation(PresentationDTO dto);
     PresentationDTO presentationTOPresentationDTO(Presentation dto);

}
