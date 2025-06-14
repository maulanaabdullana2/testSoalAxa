package com.testexa.testexa.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.testexa.testexa.model.dto.UserDto;
import com.testexa.testexa.model.entity.User;
import com.testexa.testexa.repository.UserRepository;
import com.testexa.testexa.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public static UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoleId());
    }

    public static User mapToEntity(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.username(),
                userDto.password(),
                userDto.roleId());
    }

    @Override
    public List<UserDto> findAll() {
        log.debug("request fetching data departments");
        return this.userRepository.findAll()
                .stream()
                .map(UserServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        log.debug("Request to get Department : {}", id);

        return this.userRepository.findById(id).map(UserServiceImpl::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id " + id));
    }

    @Override
    public UserDto save(UserDto entity) {
        log.debug("Request to create department : {}", entity);

        return mapToDto(this.userRepository
                .save(new User(
                        entity.id(),
                        entity.username(),
                        entity.password(),
                        entity.roleId())));

    }

    @Override
    public UserDto update(Long id, UserDto entity) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));

        user.setUsername(entity.username());
        user.setPassword(entity.password());
        user.setRoleId(entity.roleId());

        return mapToDto(userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        userRepository.delete(user);
    }

}
