package com.testexa.testexa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.testexa.testexa.model.dto.UserDto;
import com.testexa.testexa.service.BaseCrudService;
import com.testexa.testexa.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController extends BaseCrudController<UserDto,Long>{
    private final UserService userService;
    @Override
    protected BaseCrudService<UserDto,Long> getService() {
        return userService;
    }

    @Override
    public ResponseEntity<UserDto> create(@Valid UserDto entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<UserDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<UserDto> update(Long id, @Valid UserDto entity) {
        return super.update(id, entity);
    }

}
