package com.testexa.testexa.model.dto;

import com.testexa.testexa.model.enums.PermissionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private Long id;
    private PermissionType permissionType;
    private Long roleId;
}
