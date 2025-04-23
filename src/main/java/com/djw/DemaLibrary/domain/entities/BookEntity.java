package com.djw.DemaLibrary.domain.entities;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name ="books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String author;

    private String published_year;

    private int available_copies;

    public BookEntity() {
    }

    public BookEntity(UUID id, String title, String author, String published_year, int available_copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.published_year = published_year;
        this.available_copies = available_copies;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublished_year() {
        return published_year;
    }

    public void setPublished_year(String published_year) {
        this.published_year = published_year;
    }

    public int getAvailable_copies() {
        return available_copies;
    }

    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }
}