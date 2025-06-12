package com.testexa.testexa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testexa.testexa.model.dto.PermissionDto;
import com.testexa.testexa.model.dto.RoleDto;
import com.testexa.testexa.service.BaseCrudService;
import com.testexa.testexa.service.PermissionService;
import com.testexa.testexa.service.RoleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permission")
@Slf4j
@RequiredArgsConstructor
public class PermissionController extends BaseCrudController<PermissionDto,Long> {
    private final PermissionService permissionService;
    @Override
    protected BaseCrudService<PermissionDto, Long> getService() {
        return permissionService;
    }

    @Override
    public ResponseEntity<PermissionDto> create(@Valid PermissionDto entity) {
        return super.create(entity);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<List<PermissionDto>> getAll() {
        return super.getAll();
    }

    @Override
    public ResponseEntity<PermissionDto> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<PermissionDto> update(Long id, @Valid PermissionDto entity) {
        return super.update(id, entity);
    }

}
