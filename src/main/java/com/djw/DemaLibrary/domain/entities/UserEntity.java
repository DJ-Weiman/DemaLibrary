package com.djw.DemaLibrary.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String email;

    @Column(nullable = false)
    private LocalDateTime created_at;

    private String userPassword;

    public UserEntity() {
    }

    public UserEntity(UUID id, String name, String email, LocalDateTime created_at, String userPassword) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.userPassword = userPassword;
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

    @PreUpdate
    protected void onUpdate(){
        this.created_at = LocalDateTime.now();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}