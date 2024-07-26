package bg.softuni.minchevparquet.service;

import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
import bg.softuni.minchevparquet.model.entity.Parquet;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);
    Optional<MinchevParquetUserDetails> getCurrentUser();
    List<Parquet> findFavourites(UUID uuid);
}
