package bg.softuni.michevparquetsparquet.service;

import bg.softuni.michevparquetsparquet.model.dto.AddParquetDTO;
import bg.softuni.michevparquetsparquet.model.dto.ParquetDTO;
import bg.softuni.michevparquetsparquet.model.dto.RenameParquetDTO;
import bg.softuni.michevparquetsparquet.model.entity.Parquet;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;

import java.util.List;
import java.util.Optional;

public interface ParquetService {
    ParquetDTO createParquet(AddParquetDTO addParquetDTO);

    ParquetDTO getParquetById(Long id);

    ParquetDTO renameParquet(Long id, RenameParquetDTO renameParquet);

    void deleteParquet(Long parquetId);

    List<ParquetDTO> getAllParquets();

    List<ParquetDTO> getVinylParquets();
    List<ParquetDTO> getClassicParquets();
    List<ParquetDTO> getThreeLayeredParquets();
    List<ParquetDTO> getLaminateParquets();
    List<ParquetDTO> getCarpetTilesParquets();

    void cleanupOldParquets();

//    Optional<Parquet> getParquetEntityById(Long id);

//    List<ParquetDTO> getParquetsByModel(ModelName model);
}
