package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.model.entity.UserRole;
import bg.softuni.minchevparquet.model.enums.UserRoleEnum;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;
import bg.softuni.minchevparquet.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MinchevParquetUserDetailsServiceTest {
    private static final String NOT_EXISTENT_EMAIL = "nonexistent@email.com";
    private static final String TEST_EMAIL = "maya@mail";

    private MinchevParquetUserDetailsService toTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        toTest = new MinchevParquetUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_UserFound() {

        User testUser = new User();
        testUser.setEmail(TEST_EMAIL);
        testUser.setPassword("topsecret");
        testUser.setFirstName("Pesho");
        testUser.setLastName("Petrov");
        UserRole userRoleAdmin = new UserRole();
        UserRole userRoleClient = new UserRole();
        userRoleAdmin.setRole(UserRoleEnum.ADMIN);
        userRoleClient.setRole(UserRoleEnum.CLIENT);
        testUser.setRoles(List.of(userRoleAdmin, userRoleClient));

        when(mockUserRepository.findByEmail(TEST_EMAIL))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails = toTest.loadUserByUsername(TEST_EMAIL);

        Assertions.assertInstanceOf(MinchevParquetUserDetails.class, userDetails);

        MinchevParquetUserDetails minchevParquetUserDetails = (MinchevParquetUserDetails) userDetails;

        Assertions.assertEquals(TEST_EMAIL, userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUser.getFirstName(), minchevParquetUserDetails.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), minchevParquetUserDetails.getLastName());
        Assertions.assertEquals(testUser.getFirstName() + " " + testUser.getLastName(),
                minchevParquetUserDetails.getFullName());

        var expectedRoles = testUser.getRoles().stream().map(UserRole::getRole).map(r -> "ROLE_" + r).toList();
        var actualRoles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Assertions.assertEquals(expectedRoles, actualRoles);
    }


    @Test
    void testLoadUserByUsername_UserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername(NOT_EXISTENT_EMAIL)
        );
    }

}
