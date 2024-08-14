package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.enums.ModelName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddParquetDTOTest {
    @Test
    public void testGettersAndSetters() {
        AddParquetDTO dto = new AddParquetDTO();
        dto.setModelName(ModelName.VINYL);
        dto.setSize(8);
        dto.setImageUrl("imageUrl");
        dto.setPrice(22.99);
        dto.setClassRate(10);

        assertThat(dto.getClassRate()).isEqualTo(10);
        assertThat(dto.getSize()).isEqualTo(8);
        assertThat(dto.getImageUrl()).isEqualTo("imageUrl");
        assertThat(dto.getPrice()).isEqualTo(22.99);
        assertThat(dto.getModelName()).isEqualTo(ModelName.VINYL);
    }
}
