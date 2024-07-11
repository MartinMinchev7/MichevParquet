package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;
import bg.softuni.minchevparquet.model.entity.Model;
import bg.softuni.minchevparquet.model.entity.Parquet;
import bg.softuni.minchevparquet.repository.ModelRepository;
import bg.softuni.minchevparquet.repository.ParquetRepository;
import bg.softuni.minchevparquet.service.ParquetService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParquetServiceImpl implements ParquetService {
    private final ParquetRepository parquetRepository;
    private final ModelRepository modelRepository;

    public ParquetServiceImpl(ParquetRepository parquetRepository, ModelRepository modelRepository) {
        this.parquetRepository = parquetRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public boolean createParquet(AddParquetDTO addParquetDTO) {

        Optional<Parquet> byName = parquetRepository.findByName(addParquetDTO.getName());

        if (byName.isPresent()) {
            return false;
        }

        Optional<Model> byModelName = modelRepository.findByModelName(addParquetDTO.getModelName());

        if (byModelName.isEmpty()) {
            return false;
        }

        Parquet parquet = new Parquet();
        parquet.setName(addParquetDTO.getName());
        parquet.setModel(byModelName.get());
        parquet.setSize(addParquetDTO.getSize());
        parquet.setClassRate(addParquetDTO.getClassRate());
        parquet.setImageUrl(addParquetDTO.getImageUrl());

        parquetRepository.save(parquet);

        return true;
    }
}
