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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Username cannot be null")
    private String name;

    private String email;

    private String registered_date;

    private Integer current_borrow_count;

    private Integer past_borrow_count;

    private Integer remaining_borrow_count;
}
