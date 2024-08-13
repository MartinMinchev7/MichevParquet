package bg.softuni.minchevparquet.web;

import bg.softuni.minchevparquet.model.dto.AddPadDTO;
import bg.softuni.minchevparquet.model.dto.PadSummaryDTO;
import bg.softuni.minchevparquet.model.enums.PadModelName;
import bg.softuni.minchevparquet.service.exception.ObjectNotFoundException;
import bg.softuni.minchevparquet.service.impl.PadServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "testuser@mail.com",
        roles = {"USER", "ADMIN"}
)
public class PadControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PadServiceImpl padServiceImpl;

    @InjectMocks
    private PadController padController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testViewAllPads() throws Exception {
        // Prepare test data
        PadSummaryDTO pad1 = new PadSummaryDTO();
        pad1.setId(1L);
        pad1.setPrice(22.99);
        pad1.setSize(8);
        pad1.setImageUrl("image1.jpg");

        PadSummaryDTO pad2 = new PadSummaryDTO();
        pad1.setId(2L);
        pad1.setPrice(23.99);
        pad1.setSize(10);
        pad1.setImageUrl("image2.jpg");

        when(padServiceImpl.getAllPadsSummary()).thenReturn(Arrays.asList(pad1, pad2));

        // Perform request and verify response
        mockMvc.perform(MockMvcRequestBuilders.get("/pads/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("pads"))
                .andExpect(MockMvcResultMatchers.model().attribute("pads", hasSize(2)));
    }

    @Test
    public void testAddPadPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/pads/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add-pad"));
    }

    @Test
    public void testCreatePadSuccess() throws Exception {
        AddPadDTO addPadDTO = new AddPadDTO();
        addPadDTO.setPadModel(PadModelName.VILO);
        addPadDTO.setImageUrl("imageUrl");
        addPadDTO.setPrice(22.99);
        addPadDTO.setSize(8);

        mockMvc.perform(MockMvcRequestBuilders.post("/pads/add")
                        .flashAttr("padData", addPadDTO))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/pads/all"));

        verify(padServiceImpl, times(1)).createPad(addPadDTO);
    }

    @Test
    public void testCreatePadValidationFailure() throws Exception {
        AddPadDTO addPadDTO = new AddPadDTO(); // Missing required fields

        mockMvc.perform(MockMvcRequestBuilders.post("/pads/add")
                        .flashAttr("padData", addPadDTO))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/pads/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("padData"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("org.springframework.validation.BindingResult.padData"));
    }

//    @Test
//    public void testPadDetails() throws Exception {
//        Long padId = 1L;
//        PadSummaryDTO padDetails = new PadSummaryDTO();
//        padDetails.setId(padId);
//        padDetails.setPrice(22.99);
//        padDetails.setSize(8);
//        padDetails.setImageUrl("image1.jpg");
//
//        when(padServiceImpl.getPadDetails(padId)).thenReturn(padDetails);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/pads/{id}", padId))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("pad-details"))
//                .andExpect(MockMvcResultMatchers.model().attribute("padDetails", padDetails));
//    }

    @Test
    public void testDeletePad() throws Exception {
        Long padId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/pads/delete/{id}", padId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/pads/all"));

        verify(padServiceImpl, times(1)).deletePad(padId);
    }

    @Test
    public void testHandleObjectNotFound() throws Exception {
        Long padId = 1L;
        when(padServiceImpl.getPadDetails(padId)).thenThrow(new ObjectNotFoundException("object-not-found", padId));

        mockMvc.perform(MockMvcRequestBuilders.get("/pads/{id}", padId))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.view().name("object-not-found"))
                .andExpect(MockMvcResultMatchers.model().attribute("padId", padId));
    }
}
