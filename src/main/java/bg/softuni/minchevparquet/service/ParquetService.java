package bg.softuni.minchevparquet.service;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;
import bg.softuni.minchevparquet.model.dto.ParquetDetailsDTO;
import bg.softuni.minchevparquet.model.dto.ParquetSummaryDTO;
import bg.softuni.minchevparquet.model.entity.Parquet;

import java.util.List;
import java.util.UUID;

public interface ParquetService {
    void createParquet(AddParquetDTO addParquetDTO);

    void deleteParquet(long parquetId);

    ParquetDetailsDTO getParquetDetails(Long id);

    Parquet getParquet(Long id);

    void addToFavourite(UUID uuid, Long parquetId);

    List<ParquetSummaryDTO> getAllParquetsSummary();

//    List<ParquetSummaryDTO> getAllParquetsByModelNameSummary(ModelName modelName);
    List<ParquetDetailsDTO> getAllVinylParquets();
    List<ParquetSummaryDTO> getAllClassicParquets();
    List<ParquetDetailsDTO> getAllThreeLayeredParquets();
    List<ParquetDetailsDTO> getAllCarpetTilesParquets();

    List<ParquetDetailsDTO> getAllLaminateParquets();
}
