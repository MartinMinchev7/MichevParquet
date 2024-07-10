package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;
import bg.softuni.minchevparquet.model.entity.Parquet;
import bg.softuni.minchevparquet.repository.ParquetRepository;
import bg.softuni.minchevparquet.service.ParquetService;
import org.springframework.stereotype.Service;

@Service
public class ParquetServiceImpl implements ParquetService {
    private final ParquetRepository parquetRepository;

    public ParquetServiceImpl(ParquetRepository parquetRepository) {
        this.parquetRepository = parquetRepository;
    }

    @Override
    public boolean createParquet(AddParquetDTO addParquetDTO) {

        if (parquetRepository.findByName(addParquetDTO.name())) {
            return false;
        }

        Parquet parquet = new Parquet();
        parquet.setName(addParquetDTO.name());
        parquet.getModel().setModelName(addParquetDTO.modelName());
        parquet.setSize(addParquetDTO.size());
        parquet.setClassRate(addParquetDTO.classRate());
        parquet.setImageUrl(addParquetDTO.imageUrl());

        parquetRepository.save(parquet);

        return true;
    }
}
