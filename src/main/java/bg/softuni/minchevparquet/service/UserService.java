package bg.softuni.minchevparquet.service;

import bg.softuni.minchevparquet.model.dto.MakeAdminDTO;
import bg.softuni.minchevparquet.model.dto.UserRenameDTO;
import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;

import java.util.Optional;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);

    Optional<MinchevParquetUserDetails> getCurrentUser();

    void renameUser(User user, UserRenameDTO userRenameDTO);

    Optional<User> getUserDetails(Long id);

    void makeAdmin(MakeAdminDTO makeAdminDTO);
}
