package com.testexa.testexa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record UserDto(
    Long id,
    String username,
    String password,
    Long roleId
) {}
