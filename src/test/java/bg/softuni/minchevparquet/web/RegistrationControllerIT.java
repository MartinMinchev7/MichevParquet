package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.config.I18NConfig;
import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.repository.UserRepository;
import bg.softuni.minchevparquet.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "testuser@mail.com",
        roles = {"USER", "ADMIN"}
)
public class RegistrationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testGetRegister() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("registerDTO"));
    }

//    @Test
//    void testRegistration() throws Exception {
//
//        mockMvc.perform(post("/users/register")
//                        .param("email", "anna@example.com")
//                        .param("firstName", "Anna")
//                        .param("lastName", "Petrova")
//                        .param("password", "topsecret")
//                        .param("confirmPassword", "topsecret")
//                        .with(csrf())
//                ).andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/users/login"));
//
//        Optional<User> userEntityOpt = userRepository.findByEmail("anna@example.com");
//
//        Assertions.assertTrue(userEntityOpt.isPresent());
//
//        User userEntity = userEntityOpt.get();
//
//        Assertions.assertEquals("Anna", userEntity.getFirstName());
//        Assertions.assertEquals("Petrova", userEntity.getLastName());
//
//        Assertions.assertTrue(passwordEncoder.matches("topsecret", userEntity.getPassword()));
//    }

    @Test
    public void testPostRegister_Failure() throws Exception {
        mockMvc.perform(post("/users/register")
                        .flashAttr("registerDTO", new UserRegisterDTO())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"))
                .andExpect(flash().attributeExists("registerDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.registerDTO"));
    }

}
