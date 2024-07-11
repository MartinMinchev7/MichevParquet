package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;
import bg.softuni.minchevparquet.model.dto.ParquetDetailsDTO;
import bg.softuni.minchevparquet.repository.ParquetRepository;
import bg.softuni.minchevparquet.service.ParquetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ParquetServiceImpl implements ParquetService {
    private final Logger LOGGER = LoggerFactory.getLogger(ParquetServiceImpl.class);
    private final RestClient parquetRestClient;
    private final ParquetRepository parquetRepository;

    public ParquetServiceImpl(RestClient parquetRestClient, ParquetRepository parquetRepository) {
        this.parquetRestClient = parquetRestClient;
        this.parquetRepository = parquetRepository;
    }

    @Override
    public void createParquet(AddParquetDTO addParquetDTO) {
        LOGGER.debug("Creating new offer...");

        parquetRestClient
                .post()
                .uri("http://localhost:8081/parquets")
                .body(addParquetDTO)
                .retrieve();
    }

    @Override
    public void deleteParquet(long parquetId) {
        parquetRepository.deleteById(parquetId);
    }

    @Override
    public ParquetDetailsDTO getParquetDetails(Long id) {
        return parquetRestClient
                .get()
                .uri("http://localhost:8081/parquets/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ParquetDetailsDTO.class);
    }

    @Override
    public List<ParquetDetailsDTO> getAllParquetsSummary() {
        return parquetRestClient
                .get()
                .uri("http://localhost:8081/parquets")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
