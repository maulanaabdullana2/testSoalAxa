package com.testexa.testexa;

import com.testexa.testexa.model.dto.UserDto;
import com.testexa.testexa.model.entity.User;
import com.testexa.testexa.repository.UserRepository;
import com.testexa.testexa.service.UserService;
import com.testexa.testexa.service.implementation.UserServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testFindAll() {
        List<User> mockUsers = List.of(
                new User(1L, "user1", "password1", 1L),
                new User(2L, "user2", "password2", 2L));
        when(userRepository.findAll()).thenReturn(mockUsers);
        List<UserDto> result = userService.findAll();
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).username());
        assertEquals("user2", result.get(1).username());
    }

    @Test
    void testFindById() {
        User user = new User(1L, "user1", "password1", 1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserDto result = userService.findById(1L);
        assertNotNull(result);
        assertEquals("user1", result.username());
    }

    @Test
    void testFindByIdNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            userService.findById(99L);
        });
        assertEquals("Department not found with id 99", ex.getMessage());
    }

    
    @Test
    void testSave() {
        UserDto userDto = new UserDto(null, "user1", "password1", 1L);
        User user = new User(1L, "user1", "password1", 1L);
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDto result = userService.save(userDto);
        assertNotNull(result);
        assertEquals("user1", result.username());
        assertEquals(1L, result.id());
    }

    @Test
    void testUpdate() {
        User existingUser = new User(1L, "user1", "password1", 1L);
        UserDto updateDto = new UserDto(1L, "user_updated", "password_updated", 1L);
        User updatedUser = new User(1L, "user_updated", "password_updated", 1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        UserDto result = userService.update(1L, updateDto);
        assertNotNull(result);
        assertEquals("user_updated", result.username());
        assertEquals("password_updated", result.password());
    }

    @Test
    void testUpdateNotFound() {
        UserDto updateDto = new UserDto(99L, "user_updated", "password_updated", 1L);
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            userService.update(99L, updateDto);
        });

        assertEquals("User not found with id 99", ex.getMessage());
    }

    @Test
    void testDelete() {
        User user = new User(1L, "user1", "password1", 1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);
        userService.delete(1L);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            userService.delete(99L);
        });
        assertEquals("User not found with id 99", ex.getMessage());
    }

}
