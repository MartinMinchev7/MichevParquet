package bg.softuni.minchevparquet.model.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.userdetails.User;

public class AddMessageDTO {

    @NotBlank
    private String sender;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String message;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
