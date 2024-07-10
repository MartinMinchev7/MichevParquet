package bg.softuni.minchevparquet.service;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;

public interface ParquetService {
    boolean createParquet(AddParquetDTO addParquetDTO);
}
