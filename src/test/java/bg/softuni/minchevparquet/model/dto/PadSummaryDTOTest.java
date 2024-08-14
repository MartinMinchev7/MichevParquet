package bg.softuni.minchevparquet.model.dto;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PadSummaryDTOTest {
    @Test
    public void testPadSummaryDTOGettersAndSetters() {
        // Arrange
        Long id = 1L;
        Double price = 99.99;
        Integer size = 10;
        String imageUrl = "imageUrl.jpg";

        PadSummaryDTO padSummaryDTO = new PadSummaryDTO();

        // Act
        padSummaryDTO.setId(id);
        padSummaryDTO.setPrice(price);
        padSummaryDTO.setSize(size);
        padSummaryDTO.setImageUrl(imageUrl);

        // Assert
        assertThat(padSummaryDTO.getId()).isEqualTo(id);
        assertThat(padSummaryDTO.getPrice()).isEqualTo(price);
        assertThat(padSummaryDTO.getSize()).isEqualTo(size);
        assertThat(padSummaryDTO.getImageUrl()).isEqualTo(imageUrl);
    }

    @Test
    public void testPadSummaryDTODefaultConstructor() {
        // Act
        PadSummaryDTO padSummaryDTO = new PadSummaryDTO();

        // Assert
        assertThat(padSummaryDTO.getId()).isNull();
        assertThat(padSummaryDTO.getPrice()).isNull();
        assertThat(padSummaryDTO.getSize()).isNull();
        assertThat(padSummaryDTO.getImageUrl()).isNull();
    }
}
