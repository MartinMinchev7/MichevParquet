package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "maya@mail",
        roles = {"CLIENT", "ADMIN"}
)
public class RegistrationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegistrationEndpoint() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("registerData"))
                .andExpect(model().attribute("registerData", new UserRegisterDTO()));
    }
}
