package com.djw.DemaLibrary.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "borrowing")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BorrowingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID user_id;

    private UUID book_id;

    @Column(nullable = false)
    private LocalDateTime borrowed_at;

    private LocalDateTime returned_at;

    @PreUpdate
    protected void onUpdate(){
        this.borrowed_at = LocalDateTime.now();
    }
}
