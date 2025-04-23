package com.djw.DemaLibrary.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private LocalDateTime created_at;

    public UserDto() {
    }

    public UserDto(UUID id, String name, String email, LocalDateTime created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.created_at = created_at;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
