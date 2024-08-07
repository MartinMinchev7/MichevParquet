package bg.softuni.michevparquetsparquet.web;

import bg.softuni.michevparquetsparquet.model.dto.AddParquetDTO;
import bg.softuni.michevparquetsparquet.model.dto.ParquetDTO;
import bg.softuni.michevparquetsparquet.model.entity.Parquet;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;
import bg.softuni.michevparquetsparquet.service.ParquetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

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

//    @GetMapping("/add-to-favourites/{id}")
//    public ResponseEntity<Optional<Parquet>> findParquetById(@PathVariable("id") Long id) {
//        return ResponseEntity
//                .ok(parquetService.getParquetEntityById(id));
//    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
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


        ParquetDTO parquetDTO = parquetService.createParquet(addParquetDTO);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(parquetDTO.id())
                        .toUri()
        ).body(parquetDTO);
    }

    @GetMapping("/model/vinyl")
    public ResponseEntity<List<ParquetDTO>> getVinylParquets() {
        return ResponseEntity.ok(
                parquetService.getVinylParquets()
        );
    }

    @GetMapping("/model/classic")
    public ResponseEntity<List<ParquetDTO>> getClassicParquets() {
        return ResponseEntity.ok(
                parquetService.getClassicParquets()
        );
    }

    @GetMapping("/model/three-layered")
    public ResponseEntity<List<ParquetDTO>> getThreeLayeredParquets() {
        return ResponseEntity.ok(
                parquetService.getThreeLayeredParquets()
        );
    }

    @GetMapping("/model/laminate")
    public ResponseEntity<List<ParquetDTO>> getLaminateParquets() {
        return ResponseEntity.ok(
                parquetService.getLaminateParquets()
        );
    }

    @GetMapping("/model/carpet-tiles")
    public ResponseEntity<List<ParquetDTO>> getCarpetTilesParquets() {
        return ResponseEntity.ok(
                parquetService.getCarpetTilesParquets()
        );
    }

//    @GetMapping("/model/{model}")
//    public ResponseEntity<List<ParquetDTO>> getParquetsByModel(@PathVariable("model") ModelName model) {
//        return ResponseEntity.ok(
//                parquetService.getParquetsByModel(model)
//        );
//    }
}
