package bg.softuni.minchevparquet.model.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLoginDTOTest {

    @Test
    public void testGetEmail() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        String expectedEmail = "test@example.com";
        userLoginDTO.setEmail(expectedEmail);
        String actualEmail = userLoginDTO.getEmail();
        assertEquals(expectedEmail, actualEmail, "The getEmail() method should return the correct email.");
    }

    @Test
    public void testSetEmail() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        String expectedEmail = "another@example.com";
        userLoginDTO.setEmail(expectedEmail);
        assertEquals(expectedEmail, userLoginDTO.getEmail(), "The setEmail() method should correctly set the email.");
    }

    @Test
    public void testGetPassword() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        String expectedPassword = "password123";
        userLoginDTO.setPassword(expectedPassword);
        String actualPassword = userLoginDTO.getPassword();
        assertEquals(expectedPassword, actualPassword, "The getPassword() method should return the correct password.");
    }

    @Test
    public void testSetPassword() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        String expectedPassword = "secret123";
        userLoginDTO.setPassword(expectedPassword);
        assertEquals(expectedPassword, userLoginDTO.getPassword(), "The setPassword() method should correctly set the password.");
    }


}
