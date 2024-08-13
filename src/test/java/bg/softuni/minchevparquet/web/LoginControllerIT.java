package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.config.I18NConfig;
import bg.softuni.minchevparquet.model.dto.UserRenameDTO;
import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "testuser@mail.com",
        roles = {"USER", "ADMIN"}
)
public class LoginControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testViewLogin() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("loginData"));
    }

    @Test
    public void testViewLoginError() throws Exception {
        mockMvc.perform(get("/users/login-error"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("loginData"))
                .andExpect(model().attribute("showErrorMessage", true));
    }

    @Test
    public void testViewRename_UserExists() throws Exception {
        User user = new User();
        user.setId(1L);
        Mockito.when(userService.getUserDetails(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/rename/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("rename-user"))
                .andExpect(model().attributeExists("renameData"))
                .andExpect(model().attribute("userId", 1L));
    }

    @Test
    public void testViewRename_UserDoesNotExist() throws Exception {
        Mockito.when(userService.getUserDetails(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/rename/1"))
                .andExpect(status().is4xxClientError())
                .andExpect(result ->
                        assertInstanceOf(IllegalArgumentException.class, result.getResolvedException()))
                .andExpect(result ->
                        assertEquals("User not found", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void testRenameUser_Success() throws Exception {
        UserRenameDTO userRenameDTO = new UserRenameDTO();
        userRenameDTO.setFirstName("NewName");

        User user = new User();
        user.setId(1L);

        Mockito.when(userService.getUserDetails(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(patch("/users/rename/1")
                        .flashAttr("renameData", userRenameDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Mockito.verify(userService, Mockito.times(1))
                .renameUser(any(User.class), any(UserRenameDTO.class));
    }

    @Test
    public void testRenameUser_ValidationErrors() throws Exception {
        UserRenameDTO userRenameDTO = new UserRenameDTO();
        userRenameDTO.setFirstName(""); // Invalid name to trigger validation

        mockMvc.perform(patch("/users/rename/1")
                        .flashAttr("renameData", userRenameDTO)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/rename/1"))
                .andExpect(flash().attributeExists("renameData"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.renameDTO"));

        Mockito.verify(userService, Mockito.times(0)).renameUser(any(User.class), any(UserRenameDTO.class));
    }

    @Test
    public void testRenameUser_UserDoesNotExist() throws Exception {
        UserRenameDTO userRenameDTO = new UserRenameDTO();
        userRenameDTO.setFirstName("NewName");

        Mockito.when(userService.getUserDetails(1L)).thenReturn(Optional.empty());

        mockMvc.perform(patch("/users/rename/1")
                        .flashAttr("renameData", userRenameDTO)
                        .with(csrf()))
                .andExpect(status().is4xxClientError())
                .andExpect(result ->
                        assertInstanceOf(IllegalArgumentException.class, result.getResolvedException()))
                .andExpect(result ->
                        assertEquals("User not found", Objects.requireNonNull(result.getResolvedException()).getMessage()));

        Mockito.verify(userService, Mockito.times(0)).renameUser(any(User.class), any(UserRenameDTO.class));
    }
}
