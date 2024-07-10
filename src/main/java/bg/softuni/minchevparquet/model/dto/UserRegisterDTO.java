package bg.softuni.minchevparquet.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
    @NotBlank
    @Size(min = 2, max = 20, message = "First name should be between 2 and 20 symbols!")
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20, message = "Last name should be between 2 and 20 symbols!")
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 3)
    private String password;
    @NotEmpty
    @Size(min = 3)
    private String confirmPassword;

    public @NotBlank @Size(min = 2, max = 20) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank @Size(min = 2, max = 20) String firstName) {
        this.firstName = firstName;
    }

    public @NotEmpty @Size(min = 2, max = 20) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotEmpty @Size(min = 2, max = 20) String lastName) {
        this.lastName = lastName;
    }

    public @NotEmpty @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty @Email String email) {
        this.email = email;
    }

    public @NotEmpty @Size(min = 3) String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty @Size(min = 3) String password) {
        this.password = password;
    }

    public @NotEmpty @Size(min = 3) String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotEmpty @Size(min = 3) String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
