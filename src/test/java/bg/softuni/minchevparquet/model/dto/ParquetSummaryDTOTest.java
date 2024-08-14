package bg.softuni.minchevparquet.model.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParquetSummaryDTOTest {

    @Test
    public void testParquetSummaryDTOConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String name = "Oak";
        Double price = 99.99;
        String imageUrl = "oak.jpg";

        // Act
        ParquetSummaryDTO dto = new ParquetSummaryDTO(id, name, price, imageUrl);

        // Assert
        assertThat(dto.id()).isEqualTo(id);
        assertThat(dto.name()).isEqualTo(name);
        assertThat(dto.price()).isEqualTo(price);
        assertThat(dto.imageUrl()).isEqualTo(imageUrl);
    }

    @Test
    public void testParquetSummaryDTOEmpty() {
        // Act
        ParquetSummaryDTO dto = new ParquetSummaryDTO(null, null, null, null);

        // Assert
        assertThat(dto.id()).isNull();
        assertThat(dto.name()).isNull();
        assertThat(dto.price()).isNull();
        assertThat(dto.imageUrl()).isNull();
    }
}
