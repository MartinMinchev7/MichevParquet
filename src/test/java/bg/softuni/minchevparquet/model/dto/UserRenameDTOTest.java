package bg.softuni.minchevparquet.model.dto;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRenameDTOTest {
    @Test
    public void testUserRenameDTOGettersAndSetters() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";

        UserRenameDTO userRenameDTO = new UserRenameDTO();

        // Act
        userRenameDTO.setFirstName(firstName);
        userRenameDTO.setLastName(lastName);

        // Assert
        assertThat(userRenameDTO.getFirstName()).isEqualTo(firstName);
        assertThat(userRenameDTO.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void testUserRenameDTODefaultConstructor() {
        // Act
        UserRenameDTO userRenameDTO = new UserRenameDTO();

        // Assert
        assertThat(userRenameDTO.getFirstName()).isNull();
        assertThat(userRenameDTO.getLastName()).isNull();
    }
}
