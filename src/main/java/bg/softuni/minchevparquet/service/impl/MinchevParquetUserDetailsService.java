package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.model.entity.UserRole;
import bg.softuni.minchevparquet.model.enums.UserRoleEnum;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;
import bg.softuni.minchevparquet.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MinchevParquetUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MinchevParquetUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(MinchevParquetUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private static UserDetails map(User user) {
        return new MinchevParquetUserDetails(
                user.getUuid(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(UserRole::getRole).map(MinchevParquetUserDetailsService::map).toList(),
                user.getFirstName(),
                user.getLastName(),
                user.getId()
        );
    }

    private static GrantedAuthority map(UserRoleEnum role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role
        );
    }
}
