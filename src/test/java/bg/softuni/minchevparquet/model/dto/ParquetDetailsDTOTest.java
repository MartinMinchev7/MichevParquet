package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.entity.Model;
import bg.softuni.minchevparquet.model.enums.ModelName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParquetDetailsDTOTest {
    @Test
    public void testRecordConstructor() {
        // Arrange
        Long id = 1L;
        String name = "Oak";
        Model model = new Model();
        model.setModelName(ModelName.VINYL);
        model.setDescription("test");
        Integer size = 10;
        Integer classRate = 5;
        Double price = 99.99;
        String imageUrl = "oak.jpg";

        // Act
        ParquetDetailsDTO dto = new ParquetDetailsDTO(id, name, model, size, classRate, price, imageUrl);

        // Assert
        assertThat(dto.id()).isEqualTo(id);
        assertThat(dto.name()).isEqualTo(name);
        assertThat(dto.model()).isEqualTo(model);
        assertThat(dto.size()).isEqualTo(size);
        assertThat(dto.classRate()).isEqualTo(classRate);
        assertThat(dto.price()).isEqualTo(price);
        assertThat(dto.imageUrl()).isEqualTo(imageUrl);
    }

    @Test
    public void testEmptyMethod() {
        // Act
        ParquetDetailsDTO dto = ParquetDetailsDTO.empty();

        // Assert
        assertThat(dto.id()).isNull();
        assertThat(dto.name()).isNull();
        assertThat(dto.model()).isNull();
        assertThat(dto.size()).isNull();
        assertThat(dto.classRate()).isNull();
        assertThat(dto.price()).isNull();
        assertThat(dto.imageUrl()).isNull();
    }
}
