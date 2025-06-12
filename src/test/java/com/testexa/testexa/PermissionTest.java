package com.testexa.testexa;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.testexa.testexa.model.dto.PermissionDto;
import com.testexa.testexa.model.entity.Permission;
import com.testexa.testexa.model.entity.Role;
import com.testexa.testexa.model.enums.PermissionType;
import com.testexa.testexa.repository.PermissionRepository;
import com.testexa.testexa.service.implementation.PermissionImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PermissionTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionImpl permissionService;

    @Test
    void testGetAllPermissions() {
        List<Permission> mockPermissions = List.of(
                new Permission(1L, PermissionType.READ, new Role(1L, "Admin")));
        when(permissionRepository.findAll()).thenReturn(mockPermissions);
        List<PermissionDto> result = permissionService.findAll();
        assertEquals(1, result.size());
        assertEquals(PermissionType.READ, result.get(0).permissionType());
    }

    @Test
    void testCreatePermission() {
        PermissionDto permissionDto = new PermissionDto(null, PermissionType.READ_WRITE, 2L); 
        Permission savedPermission = new Permission(1L, PermissionType.READ_WRITE, new Role(2L, "Admin"));
        when(permissionRepository.save(any(Permission.class))).thenReturn(savedPermission);
        PermissionDto result = permissionService.save(permissionDto);
        assertEquals(1L, result.id(), "ID should match the saved Permission ID");
        assertEquals(PermissionType.READ_WRITE, result.permissionType(), "Permission type should match");
    }

    @Test
    void testUpdatePermission() {
        Permission existingPermission = new Permission(1L, PermissionType.READ, new Role(1L, "Admin"));
        PermissionDto updateDto = new PermissionDto(1L, PermissionType.READ_WRITE, 1L);
        Permission updatedPermission = new Permission(1L, PermissionType.READ_WRITE, new Role(1L, "Admin"));
        when(permissionRepository.findById(1L)).thenReturn(Optional.of(existingPermission));
        when(permissionRepository.save(existingPermission)).thenReturn(updatedPermission);
        PermissionDto result = permissionService.update(1L, updateDto);
        assertEquals(PermissionType.READ_WRITE, result.permissionType());
        assertEquals(1L, result.id());
    }

    @Test
    void testGetPermissionById() {
        Permission permission = new Permission(1L, PermissionType.READ, new Role(1L, "Admin"));
        when(permissionRepository.findById(1L)).thenReturn(Optional.of(permission));
        PermissionDto result = permissionService.findById(1L);
        assertEquals(1L, result.id());
        assertEquals(PermissionType.READ, result.permissionType());
    }

    @Test
    void testGetPermissionById_not_found_throwsException() {
        when(permissionRepository.findById(1L)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            permissionService.findById(1L);
        });
        assertEquals("Product not found with id 1", ex.getMessage());
    }

    @Test
    void testDeletePermission() {
        Permission permission = new Permission(1L, PermissionType.READ, new Role(1L, "Admin"));

        when(permissionRepository.findById(1L)).thenReturn(Optional.of(permission));
        doNothing().when(permissionRepository).delete(permission);

        permissionService.delete(1L);
        verify(permissionRepository, times(1)).findById(1L);
        verify(permissionRepository, times(1)).delete(permission);
    }
}
