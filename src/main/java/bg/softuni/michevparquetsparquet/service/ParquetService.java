package bg.softuni.michevparquetsparquet.service;

import bg.softuni.michevparquetsparquet.model.dto.AddParquetDTO;
import bg.softuni.michevparquetsparquet.model.dto.ParquetDTO;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;

import java.util.List;

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

//    List<ParquetDTO> getParquetsByModel(ModelName model);
}
