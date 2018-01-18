package kriss0101.cvdb.mappers;

import kriss0101.cvdb.commands.PresentationDTO;
import kriss0101.cvdb.datamodel.Presentation;
import org.junit.Test;

import static org.junit.Assert.*;

public class PresentationToPresentationDTOMapperTest {

    @Test
    public void presentationToPresentationDTO() {
        // Given
        Presentation p = new Presentation("short","long");

        // When
        PresentationDTO dto = PresentationToPresentationDTOMapper.presentationToPresentationDTO(p);


        // Then
        assertEquals(p.getShortDescription(), dto.getShortDescription());
        assertEquals(p.getLongDescription(), dto.getLongDescription());
    }

    @Test
    public void presentationDTOToPresentation() {
        // Given
        PresentationDTO dto = new PresentationDTO();
        dto.setShortDescription("short");
        dto.setLongDescription("long");

        // When
        Presentation p = PresentationToPresentationDTOMapper.presentationDTOToPresentation(dto);

        // Then
        assertEquals(p.getShortDescription(), dto.getShortDescription());
        assertEquals(p.getLongDescription(), dto.getLongDescription());

    }
}