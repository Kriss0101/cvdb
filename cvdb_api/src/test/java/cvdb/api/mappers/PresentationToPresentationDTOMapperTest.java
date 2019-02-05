package cvdb.api.mappers;

import cvdb.api.mappers.PresentationMapper;
import cvdb.api.commands.PresentationDTO;
import cvdb.api.domain.Presentation;

import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.assertEquals;

public class PresentationToPresentationDTOMapperTest {

    private PresentationMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper=Mappers.getMapper(PresentationMapper.class);
    }

    @Test
    public void presentationToPresentationDTO() {
        // Given
        Presentation p = new Presentation("short","long");

        // When
        PresentationDTO dto = mapper.presentationTOPresentationDTO(p);


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
        Presentation p = mapper.presentationDTOTOPresentation(dto);

        // Then
        assertEquals(p.getShortDescription(), dto.getShortDescription());
        assertEquals(p.getLongDescription(), dto.getLongDescription());

    }
}