//package dev.arack.enlace.iam.application.managers;
//
//import dev.arack.enlace.iam.application.port.output.persistence.UserPersistence;
//import dev.arack.enlace.iam.domain.aggregates.UserEntity;
//import dev.arack.enlace.shared.exceptions.ResourceNotFoundException;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceManagerTest {
//    @InjectMocks
//    private UserServiceManager userServiceImpl;
//    @Mock
//    private UserPersistence UserPersistence;
//
//    @Test
//    public void testGetAllUsers() {
//        // Arrange
//        UserEntity userEntity = new UserEntity();
//        List<UserEntity> userEntities = List.of(userEntity);
//        when(UserPersistence.findAll()).thenReturn(userEntities);
//
//        // Act
////        List<UserEntity> result = userServiceImpl.getAllUsers();
//
//        // Assert
////        assertEquals(userEntities, result);
//        verify(UserPersistence, times(1)).findAll();
//    }
//    @Test
//    public void testGetUserByUsername_UserFound() {
//        // Arrange
//        String username = "testUser";
//        UserEntity userEntity = new UserEntity();
////        when(IUserPersistencePort.findByUsername(username)).thenReturn(Optional.of(userEntity));
//
//        // Act
////        UserEntity result = userServiceImpl.getUserByUsername(username);
//
//        // Assert
////        assertEquals(userEntity, result);
////        verify(IUserPersistencePort, times(1)).findByUsername(username);
//    }
//    @Test
//    public void testGetUserByUsername_UserNotFound() {
//        // Arrange
//        String username = "nonExistentUser";
////        when(IUserPersistencePort.findByUsername(username)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        assertThrows(ResourceNotFoundException.class, () -> {
//            userServiceImpl.getUserByUsername(username);
//        });
////        verify(IUserPersistencePort, times(1)).findByUsername(username);
//    }
//    @Test
//    public void testUpdateUser() {
//        // Arrange
//        Long userId = 1L;
////        UserRequest userRequest = new UserRequest();
////        userRequest.setFirstName("John");
////        userRequest.setLastName("Doe");
//
//        UserEntity userEntity = new UserEntity();
////        userEntity.setFirstName("OldFirstName");
////        userEntity.setLastName("OldLastName");
//
//        when(UserPersistence.findById(userId)).thenReturn(Optional.of(userEntity));
//
//        // Act
////        userServiceImpl.updateUser(userId, userRequest);
//
//        // Assert
//        verify(UserPersistence, times(1)).findById(userId);
//        verify(UserPersistence, times(1)).updateUser(userEntity);
////        assertEquals("John", userEntity.getFirstName());
////        assertEquals("Doe", userEntity.getLastName());
//    }
//    @Test
//    public void testDeleteUser() {
//        // Arrange
//        Long userId = 1L;
//        UserEntity userEntity = new UserEntity();
//        when(UserPersistence.findById(userId)).thenReturn(Optional.of(userEntity));
//
//        // Act
//        userServiceImpl.deleteUser();
//
//        // Assert
//        verify(UserPersistence, times(1)).findById(userId);
//        verify(UserPersistence, times(1)).deleteUser(userEntity);
//    }
//}
