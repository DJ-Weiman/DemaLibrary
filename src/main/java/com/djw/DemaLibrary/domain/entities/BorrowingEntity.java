package com.djw.DemaLibrary.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "borrowing")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BorrowingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(nullable = false)
    private LocalDateTime borrowed_at;

    @Column(nullable = false)
    private LocalDateTime returnDate;

    private LocalDateTime returned_at;

    @PreUpdate
    protected void onUpdate(){
        this.borrowed_at = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BorrowingEntity that = (BorrowingEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(book, that.book) && Objects.equals(borrowed_at, that.borrowed_at) && Objects.equals(returnDate, that.returnDate) && Objects.equals(returned_at, that.returned_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, borrowed_at, returnDate, returned_at);
    }
}
