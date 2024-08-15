package bg.softuni.minchevparquet.service.impl;

import bg.softuni.minchevparquet.model.dto.MakeAdminDTO;
import bg.softuni.minchevparquet.model.dto.UserRegisterDTO;
import bg.softuni.minchevparquet.model.dto.UserRenameDTO;
import bg.softuni.minchevparquet.model.entity.User;
import bg.softuni.minchevparquet.model.entity.UserRole;
import bg.softuni.minchevparquet.model.enums.UserRoleEnum;
import bg.softuni.minchevparquet.model.user.MinchevParquetUserDetails;
import bg.softuni.minchevparquet.repository.UserRepository;
import bg.softuni.minchevparquet.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(userRegisterDTO.getFirstName(), actualSavedEntity.getFirstName());
        assertEquals(userRegisterDTO.getLastName(), actualSavedEntity.getLastName());
        assertEquals(userRegisterDTO.getPassword() + userRegisterDTO.getPassword(), actualSavedEntity.getPassword());
        assertEquals(userRegisterDTO.getConfirmPassword() + userRegisterDTO.getConfirmPassword(), actualSavedEntity.getPassword());
        assertEquals(userRegisterDTO.getEmail(), actualSavedEntity.getEmail());
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

        assertTrue(user.isPresent());
        assertEquals(userDetails, user.get());
    }

    @Test
    public void testGetCurrentUserWhenNotAuthenticated() {
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);

        Optional<MinchevParquetUserDetails> user = toTest.getCurrentUser();

        assertTrue(user.isEmpty());
    }

    @Test
    public void testGetCurrentUserWhenPrincipalIsNotUserDetails() {
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = mock(Authentication.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getPrincipal()).thenReturn("not a user details object");

        Optional<MinchevParquetUserDetails> user = toTest.getCurrentUser();

        assertTrue(user.isEmpty());
    }

    @Test
    public void testRenameUser_FirstNameAndLastNameSet() {
        User user = new User();
        user.setFirstName("OldFirstName");
        user.setLastName("OldLastName");

        UserRenameDTO userRenameDTO = new UserRenameDTO();
        userRenameDTO.setFirstName("NewFirstName");
        userRenameDTO.setLastName("NewLastName");

        toTest.renameUser(user, userRenameDTO);

        assertEquals("NewFirstName", user.getFirstName());
        assertEquals("NewLastName", user.getLastName());
    }

    @Test
    public void testRenameUser_FirstNameOnlySet() {
        User user = new User();
        user.setFirstName("OldFirstName");
        user.setLastName("OldLastName");

        UserRenameDTO userRenameDTO = new UserRenameDTO();
        userRenameDTO.setFirstName("NewFirstName");
        userRenameDTO.setLastName(null);

        toTest.renameUser(user, userRenameDTO);

        assertEquals("NewFirstName", user.getFirstName());
        assertEquals("OldLastName", user.getLastName());
    }

    @Test
    public void testRenameUser_LastNameOnlySet() {
        User user = new User();
        user.setFirstName("OldFirstName");
        user.setLastName("OldLastName");

        UserRenameDTO userRenameDTO = new UserRenameDTO();
        userRenameDTO.setFirstName(null);
        userRenameDTO.setLastName("NewLastName");

        toTest.renameUser(user, userRenameDTO);

        assertEquals("OldFirstName", user.getFirstName());
        assertEquals("NewLastName", user.getLastName());
    }

    @Test
    public void testRenameUser_NothingSet() {
        User user = new User();
        user.setFirstName("OldFirstName");
        user.setLastName("OldLastName");

        UserRenameDTO userRenameDTO = new UserRenameDTO();
        userRenameDTO.setFirstName(null);
        userRenameDTO.setLastName(null);

        toTest.renameUser(user, userRenameDTO);

        assertEquals("OldFirstName", user.getFirstName());
        assertEquals("OldLastName", user.getLastName());
    }

    @Test
    public void testMakeAdmin_IdIsNull() {
        MakeAdminDTO makeAdminDTO = new MakeAdminDTO();
        makeAdminDTO.setId(null);

        toTest.makeAdmin(makeAdminDTO);

        verify(mockUserRepository, never()).findById(anyLong());
        verify(mockUserRepository, never()).save(any(User.class));
    }

    @Test
    public void testMakeAdmin_UserNotFound() {
        MakeAdminDTO makeAdminDTO = new MakeAdminDTO();
        makeAdminDTO.setId(1L);

        when(mockUserRepository.findById(makeAdminDTO.getId())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> toTest.makeAdmin(makeAdminDTO));
    }

    @Test
    public void testMakeAdmin_UserFound() {
        MakeAdminDTO makeAdminDTO = new MakeAdminDTO();
        makeAdminDTO.setId(1L);

        User user = new User();
        UserRole adminRole = new UserRole();
        adminRole.setRole(UserRoleEnum.ADMIN);

        when(mockUserRepository.findById(makeAdminDTO.getId())).thenReturn(Optional.of(user));
        when(userRoleRepository.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole);

        toTest.makeAdmin(makeAdminDTO);

        assertTrue(user.getRoles().contains(adminRole));
        verify(mockUserRepository, times(1)).save(user);
    }

    @Test
    public void testMakeAdmin_UserAlreadyHasAdminRole() {
        MakeAdminDTO makeAdminDTO = new MakeAdminDTO();
        makeAdminDTO.setId(1L);

        User user = new User();
        UserRole adminRole = new UserRole();
        adminRole.setRole(UserRoleEnum.ADMIN);

        user.getRoles().add(adminRole);

        when(mockUserRepository.findById(makeAdminDTO.getId())).thenReturn(Optional.of(user));
        when(userRoleRepository.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole);

        toTest.makeAdmin(makeAdminDTO);

        verify(mockUserRepository, times(1)).save(user);
        assertEquals(2, user.getRoles().size());
    }
}
