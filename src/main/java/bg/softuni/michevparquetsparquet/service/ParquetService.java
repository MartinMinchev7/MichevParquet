package bg.softuni.michevparquetsparquet.service;

import bg.softuni.michevparquetsparquet.model.dto.AddParquetDTO;
import bg.softuni.michevparquetsparquet.model.dto.ParquetDTO;
import bg.softuni.michevparquetsparquet.model.entity.Parquet;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;

import java.util.List;
import java.util.Optional;

public interface ParquetService {
    ParquetDTO createParquet(AddParquetDTO addParquetDTO);

    void deleteParquet(Long parquetId);

    ParquetDTO getParquetById(Long id);

    List<ParquetDTO> getAllParquets();

    List<ParquetDTO> getVinylParquets();
    List<ParquetDTO> getClassicParquets();
    List<ParquetDTO> getThreeLayeredParquets();
    List<ParquetDTO> getLaminateParquets();
    List<ParquetDTO> getCarpetTilesParquets();

//    Optional<Parquet> getParquetEntityById(Long id);

//    List<ParquetDTO> getParquetsByModel(ModelName model);
}
