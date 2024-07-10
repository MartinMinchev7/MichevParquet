package bg.softuni.minchevparquet.service;

import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);
}
