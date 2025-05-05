package com.djw.DemaLibrary.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private UUID id;

    @NotBlank(message = "Username cannot be null")
    private String name;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Invalid Email format")
    private String email;

    private LocalDateTime created_at;
}
