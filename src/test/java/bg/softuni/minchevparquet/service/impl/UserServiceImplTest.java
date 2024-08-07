package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;
import bg.softuni.minchevparquet.repository.UserRepository;
import bg.softuni.minchevparquet.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private UserRoleRepository userRoleRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @BeforeEach
    public void setUp() {
        toTest = new UserServiceImpl(mockPasswordEncoder,
                mockUserRepository,
                new ModelMapper(),
                userRoleRepository
        );
    }

    @Test
    void testUserRegistration() {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setFirstName("Anna");
        userRegisterDTO.setLastName("Dimitrova");
        userRegisterDTO.setPassword("topsecret");
        userRegisterDTO.setConfirmPassword("topsecret");
        userRegisterDTO.setEmail("anna@example.com");

        when(mockPasswordEncoder.encode(userRegisterDTO.getPassword()))
                .thenReturn(userRegisterDTO.getPassword()+userRegisterDTO.getPassword());

        toTest.registerUser(userRegisterDTO);

        verify(mockUserRepository).save(userCaptor.capture());

        User actualSavedEntity = userCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(userRegisterDTO.getFirstName(), actualSavedEntity.getFirstName());
        Assertions.assertEquals(userRegisterDTO.getLastName(), actualSavedEntity.getLastName());
        Assertions.assertEquals(userRegisterDTO.getPassword() + userRegisterDTO.getPassword(), actualSavedEntity.getPassword());
        Assertions.assertEquals(userRegisterDTO.getConfirmPassword() + userRegisterDTO.getConfirmPassword(), actualSavedEntity.getPassword());
        Assertions.assertEquals(userRegisterDTO.getEmail(), actualSavedEntity.getEmail());
    }

    @Test
    public void testGetCurrentUserWhenAuthenticated() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);
        MinchevParquetUserDetails userDetails = mock(MinchevParquetUserDetails.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getPrincipal()).thenReturn(userDetails);

        Optional<MinchevParquetUserDetails> user = toTest.getCurrentUser();

        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(userDetails, user.get());
    }

    @Test
    public void testGetCurrentUserWhenNotAuthenticated() {
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);

        Optional<MinchevParquetUserDetails> user = toTest.getCurrentUser();

        Assertions.assertTrue(user.isEmpty());
    }

    @Test
    public void testGetCurrentUserWhenPrincipalIsNotUserDetails() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getPrincipal()).thenReturn("not a user details object");

        Optional<MinchevParquetUserDetails> user = toTest.getCurrentUser();

        Assertions.assertTrue(user.isEmpty());
    }
}
