package bg.softuni.minchevparquet.config;

import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.model.entity.UserRole;
import bg.softuni.minchevparquet.model.enums.UserRoleEnum;
import bg.softuni.minchevparquet.repository.UserRepository;
import bg.softuni.minchevparquet.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public User dataSourceInitializer(UserRepository userRepository,
                                      UserRoleRepository userRoleRepository,
                                      PasswordEncoder passwordEncoder) {

        if (userRepository.count() == 0) {

            UserRole client = new UserRole();
            client.setRole(UserRoleEnum.CLIENT);
            client.setId(1L);
            UserRole admin = new UserRole();
            admin.setRole(UserRoleEnum.ADMIN);
            admin.setId(2L);
            userRoleRepository.save(client);
            userRoleRepository.save(admin);

            User user = new User();

            user.setFirstName("Maya");
            user.setLastName("Mincheva");
            user.setEmail("maya@mail");
            user.setPassword(passwordEncoder.encode("1234"));
            user.getRoles().addAll(userRoleRepository.findAll());
            return userRepository.save(user);
        }

        return null;
    }

}
