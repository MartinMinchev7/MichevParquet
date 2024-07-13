package bg.softuni.michevparquetsparquet.service.impl;

import bg.softuni.michevparquetsparquet.model.dto.AddParquetDTO;
import bg.softuni.michevparquetsparquet.model.dto.ParquetDTO;
import bg.softuni.michevparquetsparquet.model.entity.Model;
import bg.softuni.michevparquetsparquet.model.entity.Parquet;
import bg.softuni.michevparquetsparquet.model.enums.ModelName;
import bg.softuni.michevparquetsparquet.repository.ModelRepository;
import bg.softuni.michevparquetsparquet.repository.ParquetRepository;
import bg.softuni.michevparquetsparquet.service.ParquetService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public ParquetDTO createParquet(AddParquetDTO addParquetDTO) {
        Optional<Parquet> byName = parquetRepository.findByName(addParquetDTO.name());

        if (byName.isPresent()) {
            throw new IllegalArgumentException("Parquet with the same name already exists!");
        }

        Optional<Model> byModelName = modelRepository.findByModelName(addParquetDTO.modelName());

        if (byModelName.isEmpty()) {
            throw new IllegalArgumentException("Model not found!");
        }

        Parquet parquet = new Parquet();
        parquet.setName(addParquetDTO.name());
        parquet.setModel(byModelName.get());
        parquet.setSize(addParquetDTO.size());
        parquet.setClassRate(addParquetDTO.classRate());
        parquet.setPrice(addParquetDTO.price());
        parquet.setImageUrl(addParquetDTO.imageUrl());

        parquetRepository.save(parquet);

        return map(parquet);
    }

    @Override
    public void deleteParquet(Long parquetId) {
        parquetRepository.deleteById(parquetId);
    }

    @Override
    public ParquetDTO getParquetById(Long id) {
        return parquetRepository
                .findById(id)
                .map(ParquetServiceImpl::map)
                .orElseThrow(() -> new IllegalArgumentException("Not found!"));
    }

    @Override
    public List<ParquetDTO> getAllParquets() {
        return parquetRepository
               .findAll()
               .stream()
               .map(ParquetServiceImpl::map)
               .toList();
    }

    @Override
    public List<ParquetDTO> getParquetsByModel(ModelName model) {
        return parquetRepository
               .findAllByModelModelName(model)
               .stream()
               .map(ParquetServiceImpl::map)
               .toList();
    }

    private static ParquetDTO map(Parquet parquet) {
        return new ParquetDTO(
                parquet.getId(),
                parquet.getName(),
                parquet.getModel(),
                parquet.getSize(),
                parquet.getClassRate(),
                parquet.getSize(),
                parquet.getImageUrl()
        );
    }
}
