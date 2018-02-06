package kriss0101.cvdb.api.mappers;

import kriss0101.cvdb.api.commands.PresentationDTO;
import kriss0101.cvdb.api.datamodel.Presentation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PresentationToPresentationDTOMapperTest {

    @Test
    public void presentationToPresentationDTO() {
        // Given
        Presentation p = new Presentation("short","long");

        // When
        PresentationDTO dto = PresentationMapper.INSTANCE.presentationTOPresentationDTO(p);


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
        Presentation p = PresentationMapper.INSTANCE.presentationDTOTOPresentation(dto);

        // Then
        assertEquals(p.getShortDescription(), dto.getShortDescription());
        assertEquals(p.getLongDescription(), dto.getLongDescription());

    }
}