package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.AddParquetDTO;
import bg.softuni.minchevparquet.model.dto.ParquetDetailsDTO;
import bg.softuni.minchevparquet.model.dto.ParquetSummaryDTO;
import bg.softuni.minchevparquet.model.entity.Parquet;
import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.repository.UserRepository;
import bg.softuni.minchevparquet.service.ParquetService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class ParquetServiceImpl implements ParquetService {
    private final Logger LOGGER = LoggerFactory.getLogger(ParquetServiceImpl.class);
    private final RestClient parquetRestClient;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ParquetServiceImpl(RestClient parquetRestClient, UserRepository userRepository, ModelMapper modelMapper) {
        this.parquetRestClient = parquetRestClient;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createParquet(AddParquetDTO addParquetDTO) {
        parquetRestClient
                .post()
                .uri("http://localhost:8081/parquets")
                .body(addParquetDTO)
                .retrieve();
    }

    @Override
    public void deleteParquet(long parquetId) {
        parquetRestClient
                .delete()
                .uri("http://localhost:8081/parquets/{id}", parquetId)
                .retrieve();
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

//    @Override
//    public void addToFavourite(Long id, Long parquetId) {
//        Optional<User> byId = userRepository.findById(id);
//
//        if (byId.isEmpty()) {
//            return;
//        }
//
//        ParquetDetailsDTO parquetDetails = getParquetDetails(parquetId);
//
//        Parquet parquet = modelMapper.map(parquetDetails, Parquet.class);
//
//        byId.get().addFavourite(parquet);
//        userRepository.save(byId.get());
//    }

    @Override
    public List<ParquetSummaryDTO> getAllParquetsSummary() {
        return parquetRestClient
                .get()
                .uri("http://localhost:8081/parquets")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

//    @Override
//    public List<ParquetSummaryDTO> getAllParquetsByModelNameSummary(ModelName modelName) {
//        return parquetRestClient
//                .get()
//                .uri("http://localhost:8081/parquets/{model}", modelName)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .body(new ParameterizedTypeReference<>() {});
//    }

    @Override
    public List<ParquetDetailsDTO> getAllVinylParquets() {
        return  parquetRestClient
                .get()
                .uri("http://localhost:8081/parquets/model/vinyl")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public List<ParquetSummaryDTO> getAllClassicParquets() {
        return parquetRestClient
                .get()
                .uri("http://localhost:8081/parquets/model/classic")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public List<ParquetDetailsDTO> getAllThreeLayeredParquets() {
        return parquetRestClient
                .get()
                .uri("http://localhost:8081/parquets/model/three-layered")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public List<ParquetDetailsDTO> getAllLaminateParquets() {
        return parquetRestClient
                .get()
                .uri("http://localhost:8081/parquets/model/laminate")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public List<ParquetDetailsDTO> getAllCarpetTilesParquets() {
        return parquetRestClient
                .get()
                .uri("http://localhost:8081/parquets/model/carpet-tiles")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
