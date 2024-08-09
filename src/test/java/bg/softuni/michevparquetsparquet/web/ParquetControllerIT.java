package bg.softuni.michevparquetsparquet.web;

import bg.softuni.michevparquetsparquet.model.dto.ParquetDTO;
import bg.softuni.michevparquetsparquet.model.entity.Model;
import bg.softuni.michevparquetsparquet.model.entity.Parquet;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;
import bg.softuni.michevparquetsparquet.repository.ModelRepository;
import bg.softuni.michevparquetsparquet.repository.ParquetRepository;
import bg.softuni.michevparquetsparquet.service.ParquetService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "pesho@example.com",
        roles = {"CLIENT", "ADMIN"}
)
public class ParquetControllerIT {

    @Autowired
    private ParquetRepository parquetRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ParquetService parquetService;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void tearDown() {
        parquetRepository.deleteAll();
        modelRepository.deleteAll();
    }

    @Test
    public void testFindById() throws Exception {
        var actualEntity = createTestParquet();

        ResultActions result = mockMvc.perform(get("/parquets/{id}", actualEntity.getId())
                .contentType(MediaType.APPLICATION_JSON));

                result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)actualEntity.getId())))
                .andExpect(jsonPath("$.name", is(actualEntity.getName())))
                .andExpect(jsonPath("$.size", is(actualEntity.getSize())))
                .andExpect(jsonPath("$.classRate", is(actualEntity.getClassRate())))
                .andExpect(jsonPath("$.price", is(actualEntity.getPrice())))
                .andExpect(jsonPath("$.imageUrl", is(actualEntity.getImageUrl())));
    }

    @Test
    public void testCreateParquet() throws Exception {
        Model model;
        try {
            model = new Model();
            model.setModelName(ModelName.CLASSIC);
            model.setDescription("test description");
            modelRepository.saveAndFlush(model);
        } catch (DataIntegrityViolationException e) {
            model = modelRepository.findByModelName(ModelName.CLASSIC).orElseThrow(() -> new Exception("Model not found"));
        }

        MvcResult result = mockMvc.perform(post("/parquets")
               .contentType(MediaType.APPLICATION_JSON)
               .content("""
               {
                   "name": "meric",
                   "size": 8,
                   "classRate": 20,
                   "price": 100,
                   "imageUrl": "imageUrl",
                   "modelName": "CLASSIC"
               }
               """)
        ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String body = result.getResponse().getContentAsString();

        int id = JsonPath.read(body, "$.id");

        Optional<Parquet> createdParquetOpt = parquetRepository.findById((long)id);

        Assertions.assertTrue(createdParquetOpt.isPresent());

        Parquet parquet = createdParquetOpt.get();

        Assertions.assertEquals("meric", parquet.getName());
        Assertions.assertEquals(8, parquet.getSize());
        Assertions.assertEquals(20, parquet.getClassRate());
        Assertions.assertEquals(100, parquet.getPrice());
        Assertions.assertEquals("imageUrl", parquet.getImageUrl());
        Assertions.assertEquals(ModelName.CLASSIC, parquet.getModel().getModelName());
    }

    @Test
    public void testDeleteParquet() throws Exception {
        var actualEntity = createTestParquet();

        mockMvc.perform(delete("/parquets/{id}", actualEntity.getId())
               .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

        Assertions.assertTrue(
                parquetRepository.findById(actualEntity.getId()).isEmpty()
        );
    }

    @Test
    public void testGetVinylParquets() throws Exception {
        Model model;
        try {
            model = new Model();
            model.setModelName(ModelName.VINYL);
            model.setDescription("test description");
            modelRepository.saveAndFlush(model);
        } catch (DataIntegrityViolationException e) {
            model = modelRepository.findByModelName(ModelName.VINYL).orElseThrow(() -> new Exception("Model not found"));
        }

        Parquet parquet = createTestParquet();
        parquet.setModel(model);

        parquetRepository.save(parquet);

        List<ParquetDTO> vinylParquets = parquetService.getVinylParquets();

        String expectedJson = "[{'id':" + vinylParquets.getFirst().id() + "," +
                "'name':'meric'," +
                "'size':8," +
                "'classRate':20," +
                "'price':100," +
                "'imageUrl':'imageUrl'," +
                "'model':{'modelName':'VINYL','description':'test description'}}]";

        mockMvc.perform(get("/parquets/model/vinyl")
               .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    public void testGetClassicParquets() throws Exception {
        Model model;
        try {
            model = new Model();
            model.setModelName(ModelName.CLASSIC);
            model.setDescription("test description");
            modelRepository.saveAndFlush(model);
        } catch (DataIntegrityViolationException e) {
            model = modelRepository.findByModelName(ModelName.CLASSIC).orElseThrow(() -> new Exception("Model not found"));
        }

        Parquet parquet = createTestParquet();
        parquet.setModel(model);

        parquetRepository.save(parquet);

        List<ParquetDTO> classicParquets = parquetService.getClassicParquets();

        String expectedJson = "[{'id':" + classicParquets.getFirst().id() + "," +
                "'name':'meric'," +
                "'size':8," +
                "'classRate':20," +
                "'price':100," +
                "'imageUrl':'imageUrl'," +
                "'model':{'modelName':'CLASSIC','description':'test description'}}]";

        mockMvc.perform(get("/parquets/model/classic")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    public void testGetThreeLayeredParquets() throws Exception {
        Model model;
        try {
            model = new Model();
            model.setModelName(ModelName.THREE_LAYERED);
            model.setDescription("test description");
            modelRepository.saveAndFlush(model);
        } catch (DataIntegrityViolationException e) {
            model = modelRepository.findByModelName(ModelName.THREE_LAYERED).orElseThrow(() -> new Exception("Model not found"));
        }

        Parquet parquet = createTestParquet();
        parquet.setModel(model);

        parquetRepository.save(parquet);

        List<ParquetDTO> threeLayeredParquets = parquetService.getThreeLayeredParquets();

        String expectedJson = "[{'id':" + threeLayeredParquets.getFirst().id() + "," +
                "'name':'meric'," +
                "'size':8," +
                "'classRate':20," +
                "'price':100," +
                "'imageUrl':'imageUrl'," +
                "'model':{'modelName':'THREE_LAYERED','description':'test description'}}]";

        mockMvc.perform(get("/parquets/model/three-layered")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    public void testGetLaminateParquets() throws Exception {
        Model model;
        try {
            model = new Model();
            model.setModelName(ModelName.LAMINATE);
            model.setDescription("test description");
            modelRepository.saveAndFlush(model);
        } catch (DataIntegrityViolationException e) {
            model = modelRepository.findByModelName(ModelName.LAMINATE).orElseThrow(() -> new Exception("Model not found"));
        }

        Parquet parquet = createTestParquet();
        parquet.setModel(model);

        parquetRepository.save(parquet);

        List<ParquetDTO> laminateParquets = parquetService.getLaminateParquets();

        String expectedJson = "[{'id':" + laminateParquets.getFirst().id() + "," +
                "'name':'meric'," +
                "'size':8," +
                "'classRate':20," +
                "'price':100," +
                "'imageUrl':'imageUrl'," +
                "'model':{'modelName':'LAMINATE','description':'Laminate parquet is a versatile and cost-effective flooring option that mimics the look of traditional wood parquet.'}}]";

        mockMvc.perform(get("/parquets/model/laminate")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    public void testGetCarpetTilesParquets() throws Exception {
        Model model;
        try {
            model = new Model();
            model.setModelName(ModelName.CARPET_TILES);
            model.setDescription("test description");
            modelRepository.saveAndFlush(model);
        } catch (DataIntegrityViolationException e) {
            model = modelRepository.findByModelName(ModelName.CARPET_TILES).orElseThrow(() -> new Exception("Model not found"));
        }

        Parquet parquet = createTestParquet();
        parquet.setModel(model);

        parquetRepository.save(parquet);

        List<ParquetDTO> carpetTilesParquets = parquetService.getCarpetTilesParquets();

        String expectedJson = "[{'id':" + carpetTilesParquets.getFirst().id() + "," +
                "'name':'meric'," +
                "'size':8," +
                "'classRate':20," +
                "'price':100," +
                "'imageUrl':'imageUrl'," +
                "'model':{'modelName':'CARPET_TILES','description':'test description'}}]";

        mockMvc.perform(get("/parquets/model/carpet-tiles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }


    private Parquet createTestParquet() {
        Parquet parquet = new Parquet();
        parquet.setName("meric");
        parquet.setSize(8);
        parquet.setClassRate(20);
        parquet.setPrice(100);
        parquet.setImageUrl("imageUrl");
        parquet.setCreated(Instant.MAX);

        return parquetRepository.save(parquet);
    }

//    @Test
//    public void testParquetNotFound() throws Exception {
//        mockMvc.perform(get("/parquets/{id}", "1000000")
//                        .contentType(MediaType.APPLICATION_JSON))
//               .andExpect(status().isNotFound());
//    }
}
