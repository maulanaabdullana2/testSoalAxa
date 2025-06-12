package com.testexa.testexa.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.testexa.testexa.model.dto.PermissionDto;
import com.testexa.testexa.model.entity.Permission;
import com.testexa.testexa.model.entity.Role;
import com.testexa.testexa.repository.PermissionRepository;
import com.testexa.testexa.service.PermissionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
   public static Permission mapToEntity(PermissionDto dto) {
    return Permission.builder()
            .id(dto.id())
            .permissionType(dto.permissionType())
            .role(new Role(dto.roleId(), null))
            .build();
}

public static PermissionDto mapToDto(Permission permission) {
    return new PermissionDto(
        permission.getId(),
        permission.getPermissionType(),
        permission.getRole() != null ? permission.getRole().getId() : null
    );
}


    @Override
    public List<PermissionDto> findAll() {
       log.debug("Request Fetching Data Categories");
        return this.permissionRepository.findAll()
                .stream()
                .map(PermissionImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PermissionDto findById(Long id) {
         log.debug("Request to get Department : {}", id);

        return this.permissionRepository.findById(id).map(PermissionImpl::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
    }

    @Override
    public PermissionDto save(PermissionDto entity) {
        log.debug("Request to create Employee : {}", entity);

        var product = mapToEntity(entity);

        return mapToDto(this.permissionRepository.save(product));
    }

 @Override
public PermissionDto update(Long id, PermissionDto entity) {
    log.debug("Request to update Permission : {}", id);

    var permission = this.permissionRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Permission not found with id " + id));

    permission.setPermissionType(entity.permissionType());
    permission.setRole(new Role(entity.roleId(), null));

    permission = this.permissionRepository.save(permission);
    return mapToDto(permission);
}


    @Override
    public void delete(Long id) {
         log.debug("Request to delete Department : {}", id);

        var product = this.permissionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find product with id " + id));

        this.permissionRepository.delete(product);
    }
    
}
