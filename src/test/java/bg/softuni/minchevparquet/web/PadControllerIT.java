package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.entity.Pad;
import bg.softuni.minchevparquet.repository.PadModelRepository;
import bg.softuni.minchevparquet.repository.PadRepository;
import bg.softuni.minchevparquet.service.exception.ObjectNotFoundException;
import bg.softuni.minchevparquet.service.impl.PadServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "testuser@mail.com",
        roles = {"USER", "ADMIN"}
)
public class PadControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PadServiceImpl padServiceImpl;

    @Autowired
    private PadRepository padRepository;

    @Autowired
    private PadModelRepository padModelRepository;


    @AfterEach
    public void tearDown() {
        padRepository.deleteAll();
        padModelRepository.deleteAll();
    }


    @Test
    public void testAddPadPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pads/add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-pad"));
    }

    @Test
    public void testCreatePadSuccess() throws Exception {
        mockMvc.perform(post("/pads/add").with(csrf())
                        .param("padModel", "VILO")
                        .param("imageUrl", "image1.jpg")
                        .param("size", "8")
                        .param("price", "22.99")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pads/all"));
    }

    @Test
    public void testCreatePadValidationFailure() throws Exception {
        mockMvc.perform(post("/pads/add").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pads/add"));
    }


    @Test
    public void testDeletePad() throws Exception {
        var actualEntity = createTestPad();

        mockMvc.perform(delete("/pads/delete/{id}", actualEntity.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pads/all"));

    }

    @Test
    public void testHandleObjectNotFound() throws Exception {
        Long padId = 1L;
        when(padServiceImpl.getPadDetails(padId)).thenThrow(new ObjectNotFoundException("object-not-found", padId));

        mockMvc.perform(MockMvcRequestBuilders.get("/pads/{id}", padId))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.view().name("object-not-found"))
                .andExpect(MockMvcResultMatchers.model().attribute("padId", padId));
    }

    private Pad createTestPad() {
        Pad pad = new Pad();
        pad.setId(1L);
        pad.setImageUrl("image1.jpg");
        pad.setPrice(22.99);
        pad.setSize(8);

        return padRepository.save(pad);
    }
}
