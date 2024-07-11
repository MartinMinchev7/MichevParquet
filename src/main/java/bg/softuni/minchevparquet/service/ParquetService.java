package bg.softuni.minchevparquet.service;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;
import bg.softuni.minchevparquet.model.dto.ParquetDetailsDTO;

import java.util.List;

public interface ParquetService {
    void createParquet(AddParquetDTO addParquetDTO);

    void deleteParquet(long parquetId);

    ParquetDetailsDTO getParquetDetails(Long id);

    List<ParquetDetailsDTO> getAllParquetsSummary();
}
