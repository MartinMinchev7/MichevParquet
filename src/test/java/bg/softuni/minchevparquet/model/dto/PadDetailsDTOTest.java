package bg.softuni.minchevparquet.model.dto;

import bg.softuni.minchevparquet.model.entity.PadModel;
import bg.softuni.minchevparquet.model.enums.PadModelName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PadDetailsDTOTest {

    @Test
    public void testPadDetailsDTOGettersAndSetters() {
        // Arrange
        Long id = 1L;
        Integer size = 10;
        Double price = 199.99;
        PadModel padModel = new PadModel(PadModelName.VILO); // Example enum value
        String imageUrl = "http://example.com/image.jpg";

        PadDetailsDTO padDetailsDTO = new PadDetailsDTO();

        // Act
        padDetailsDTO.setId(id);
        padDetailsDTO.setSize(size);
        padDetailsDTO.setPrice(price);
        padDetailsDTO.setPadModel(padModel);
        padDetailsDTO.setImageUrl(imageUrl);

        // Assert
        assertThat(padDetailsDTO.getId()).isEqualTo(id);
        assertThat(padDetailsDTO.getSize()).isEqualTo(size);
        assertThat(padDetailsDTO.getPrice()).isEqualTo(price);
        assertThat(padDetailsDTO.getPadModel()).isEqualTo(padModel);
        assertThat(padDetailsDTO.getImageUrl()).isEqualTo(imageUrl);
    }

    @Test
    public void testPadDetailsDTODefaultConstructor() {
        // Act
        PadDetailsDTO padDetailsDTO = new PadDetailsDTO();

        // Assert
        assertThat(padDetailsDTO.getId()).isNull();
        assertThat(padDetailsDTO.getSize()).isNull();
        assertThat(padDetailsDTO.getPrice()).isNull();
        assertThat(padDetailsDTO.getPadModel()).isNull();
        assertThat(padDetailsDTO.getImageUrl()).isNull();
    }
}
