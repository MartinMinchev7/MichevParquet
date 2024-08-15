package bg.softuni.minchevparquet.model.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MakeAdminDTOTest {

    @Test
    public void testGetId() {
        MakeAdminDTO makeAdminDTO = new MakeAdminDTO();
        Long expectedId = 123L;
        makeAdminDTO.setId(expectedId);
        Long actualId = makeAdminDTO.getId();
        assertEquals(expectedId, actualId, "The getId() method should return the correct ID.");
    }

    @Test
    public void testSetId() {
        MakeAdminDTO makeAdminDTO = new MakeAdminDTO();
        Long expectedId = 456L;
        makeAdminDTO.setId(expectedId);
        assertEquals(expectedId, makeAdminDTO.getId(), "The setId() method should correctly set the ID.");
    }
}
