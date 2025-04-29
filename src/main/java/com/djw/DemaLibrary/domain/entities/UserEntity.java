package com.djw.DemaLibrary.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id", nullable = false)
    private AuthorityEntity authority;
}