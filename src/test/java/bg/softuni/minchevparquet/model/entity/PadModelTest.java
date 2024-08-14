package bg.softuni.minchevparquet.model.entity;

import bg.softuni.minchevparquet.model.enums.PadModelName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PadModelTest {
    @Test
    public void testPadModelGettersAndSetters() {
        // Arrange
        PadModel padModel = new PadModel();
        PadModelName padModelName = PadModelName.VILO; // Example enum value
        long id = 1L;

        // Act
        padModel.setId(id);
        padModel.setPadModelName(padModelName);

        // Assert
        assertThat(padModel.getId()).isEqualTo(id);
        assertThat(padModel.getPadModelName()).isEqualTo(padModelName);
    }

    @Test
    public void testPadModelConstructorWithArguments() {
        // Arrange
        PadModelName padModelName = PadModelName.VILO; // Example enum value

        // Act
        PadModel padModel = new PadModel(padModelName);

        // Assert
        assertThat(padModel.getPadModelName()).isEqualTo(padModelName);
        // Optionally, you can assert that id is not set if default value is used
        assertThat(padModel.getId()).isZero();
    }

    @Test
    public void testPadModelDefaultConstructor() {
        // Act
        PadModel padModel = new PadModel();

        // Assert
        assertThat(padModel.getPadModelName()).isNull();
        // Optionally, you can assert that id is not set if default value is used
        assertThat(padModel.getId()).isZero();
    }
}
