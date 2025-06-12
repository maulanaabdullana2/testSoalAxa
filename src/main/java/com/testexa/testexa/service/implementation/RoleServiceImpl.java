package com.testexa.testexa.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.testexa.testexa.model.dto.RoleDto;
import com.testexa.testexa.model.dto.UserDto;
import com.testexa.testexa.model.entity.Role;
import com.testexa.testexa.model.entity.User;
import com.testexa.testexa.repository.RoleRepository;
import com.testexa.testexa.repository.UserRepository;
import com.testexa.testexa.service.RoleService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public static RoleDto mapToDto(Role role) {
        return new RoleDto(
               role.getId(),
               role.getRoleName()
            );
    }

     public static Role mapToEntity(RoleDto roleDto) {
        return new Role(
                roleDto.getId(),
                roleDto.getRoleName()
            );
    }


    @Override
    public List<RoleDto> findAll() {
      log.debug("request fetching data departments");
        return this.roleRepository.findAll()
                .stream()
                .map(RoleServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Long id) {
        log.debug("Request to get Department : {}", id);

        return this.roleRepository.findById(id).map(RoleServiceImpl::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id " + id));
    }

    @Override
    public RoleDto save(RoleDto entity) {
    log.debug("Request to create department : {}", entity);

        return mapToDto(this.roleRepository
                .save(new Role(
                        entity.getId(),
                        entity.getRoleName()
                )));
    }

    @Override
    public RoleDto update(Long id, RoleDto entity) {
          Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        role.setId(entity.getId());
        role.setRoleName(entity.getRoleName());
        return mapToDto(roleRepository.save(role));
    }

    @Override
    public void delete(Long id) {
          if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        roleRepository.deleteById(id);
    }

}
