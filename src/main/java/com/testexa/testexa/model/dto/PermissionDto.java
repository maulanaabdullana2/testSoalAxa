package com.testexa.testexa.model.dto;

import com.testexa.testexa.model.enums.PermissionType;

public record PermissionDto(
    Long id,
    PermissionType permissionType,
    Long roleId
) {}
