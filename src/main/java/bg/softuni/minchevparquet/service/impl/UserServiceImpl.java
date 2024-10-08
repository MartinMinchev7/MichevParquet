package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.MakeAdminDTO;
import bg.softuni.minchevparquet.model.dto.UserRenameDTO;
import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
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

import java.util.Optional;

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

    @Override
    public void renameUser(User user, UserRenameDTO userRenameDTO) {
        if (userRenameDTO.getFirstName() != null) {
            user.setFirstName(userRenameDTO.getFirstName());
        }
        if (userRenameDTO.getLastName()!= null) {
            user.setLastName(userRenameDTO.getLastName());
        }
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserDetails(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void makeAdmin(MakeAdminDTO makeAdminDTO) {
        if (makeAdminDTO.getId() != null) {
            User adminUser = userRepository.findById(makeAdminDTO.getId())
                   .orElseThrow(() -> new IllegalArgumentException("User not found"));

            adminUser.getRoles().add(userRoleRepository.findByRole(UserRoleEnum.ADMIN));
            userRepository.save(adminUser);
        }
    }


    private User map(UserRegisterDTO userRegisterDTO) {
        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        UserRole clientRole = userRoleRepository.findByRole(UserRoleEnum.CLIENT);
        user.getRoles().add(clientRole);

        return user;
    }
}
