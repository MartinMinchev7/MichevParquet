package bg.softuni.michevparquetsparquet.service;

import bg.softuni.michevparquetsparquet.model.dto.AddParquetDTO;
import bg.softuni.michevparquetsparquet.model.dto.ParquetDTO;

import java.util.List;

public interface ParquetService {
    void createParquet(AddParquetDTO addParquetDTO);

    void deleteParquet(Long parquetId);

    ParquetDTO getParquetById(Long id);

    List<ParquetDTO> getAllParquets();
}
