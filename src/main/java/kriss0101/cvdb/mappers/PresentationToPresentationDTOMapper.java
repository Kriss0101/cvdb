package kriss0101.cvdb.mappers;

import kriss0101.cvdb.commands.PresentationDTO;
import kriss0101.cvdb.datamodel.Presentation;
import org.mapstruct.Mapper;

import java.sql.PreparedStatement;


public class PresentationToPresentationDTOMapper {


    public static PresentationDTO presentationToPresentationDTO(Presentation presentation) {
        PresentationDTO pDTO = new PresentationDTO();
        pDTO.setShortDescription(presentation.getShortDescription());
        pDTO.setLongDescription(presentation.getLongDescription());
        return pDTO;
    }
    public static Presentation presentationDTOToPresentation(PresentationDTO dto) {
        Presentation p = new Presentation(
                dto.getShortDescription(),
                dto.getLongDescription()
        );
        return p;
    }
}
