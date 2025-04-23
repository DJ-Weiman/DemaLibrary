package com.djw.DemaLibrary.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "borrowing")
public class BorrowingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID user_id;

    private UUID book_id;

    @Column(nullable = false)
    private LocalDateTime borrowed_at;

    private LocalDateTime returned_at;


    public BorrowingEntity() {
    }

    public BorrowingEntity(UUID id, UUID user_id, UUID book_id, LocalDateTime borrowed_at, LocalDateTime returned_at) {
        this.id = id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.borrowed_at = borrowed_at;
        this.returned_at = returned_at;
    }



    @PreUpdate
    protected void onUpdate(){
        this.borrowed_at = LocalDateTime.now();
    }
}
