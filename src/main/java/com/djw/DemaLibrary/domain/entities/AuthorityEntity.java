package com.djw.DemaLibrary.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "authority")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String authorityTitle;

}
