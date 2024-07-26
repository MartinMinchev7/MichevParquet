package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
import bg.softuni.minchevparquet.model.entity.Parquet;
import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.model.entity.UserRole;
import bg.softuni.minchevparquet.model.enums.UserRoleEnum;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;
import bg.softuni.minchevparquet.repository.UserRepository;
import bg.softuni.minchevparquet.repository.UserRoleRepository;
import bg.softuni.minchevparquet.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        userRepository.save(map(userRegisterDTO));
    }

    @Override
    public Optional<MinchevParquetUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null &&
                authentication.getPrincipal() instanceof MinchevParquetUserDetails minchevParquetUserDetails) {
            return Optional.of(minchevParquetUserDetails);
        }

        return Optional.empty();
    }

    public List<Parquet> findFavourites(UUID uuid) {
        return userRepository.findByUuid(uuid)
               .map(User::getFavouriteParquets)
               .orElse(new ArrayList<>());
    }

    private User map(UserRegisterDTO userRegisterDTO) {
        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        UserRole clientRole = userRoleRepository.findByRole(UserRoleEnum.CLIENT);
        user.getRoles().add(clientRole);

        return user;
    }
}
