package bg.softuni.minchevparquet.service;

import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;

import java.util.Optional;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);
    Optional<MinchevParquetUserDetails> getCurrentUser();
}
