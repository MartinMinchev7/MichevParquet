package bg.softuni.michevparquetsparquet.web;

import bg.softuni.michevparquetsparquet.model.dto.AddParquetDTO;
import bg.softuni.michevparquetsparquet.model.dto.ParquetDTO;
import bg.softuni.michevparquetsparquet.service.ParquetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/parquets")
public class ParquetController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParquetController.class);
    private final ParquetService parquetService;

    public ParquetController(ParquetService parquetService) {
        this.parquetService = parquetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParquetDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok(parquetService.getParquetById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ParquetDTO> deleteById(@PathVariable("id") Long id) {
        parquetService.deleteParquet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ParquetDTO>> getAllParquets() {
        return ResponseEntity.ok(
                parquetService.getAllParquets()
        );
    }

    @PostMapping
    public ResponseEntity<ParquetDTO> createParquet(@RequestBody AddParquetDTO addParquetDTO) {
        LOGGER.info("Going to create an offer {}", addParquetDTO);

        ParquetDTO parquetDTO = parquetService.createParquet(addParquetDTO);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(parquetDTO.id())
                        .toUri()
        ).body(parquetDTO);
    }
}
