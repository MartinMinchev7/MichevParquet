package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.enums.ModelName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParquetRenameDTOTest {

    @Test
    public void testGettersAndSetters() {
        ParquetRenameDTO parquetRenameDTO = new ParquetRenameDTO();
        parquetRenameDTO.setName("name");

        assertThat(parquetRenameDTO.getName()).isEqualTo("name");
    }
}
