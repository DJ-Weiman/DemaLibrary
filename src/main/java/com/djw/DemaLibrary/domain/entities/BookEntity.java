package com.djw.DemaLibrary.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name ="books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String author;

    private String genre;

    private float rating;

    private String summary;

    private String coverUrl;

    private int published_year;

    private int total_copies;

    private int available_copies;

    @ManyToMany(mappedBy = "borrowedBooks")
    private List<UserEntity> borrowers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Float.compare(rating, that.rating) == 0 && published_year == that.published_year && total_copies == that.total_copies && available_copies == that.available_copies && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(author, that.author) && Objects.equals(genre, that.genre) && Objects.equals(summary, that.summary) && Objects.equals(coverUrl, that.coverUrl) && Objects.equals(borrowers, that.borrowers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, rating, summary, coverUrl, published_year, total_copies, available_copies, borrowers);
    }
}