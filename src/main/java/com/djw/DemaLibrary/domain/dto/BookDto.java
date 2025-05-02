package com.djw.DemaLibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private UUID id;
    private String title;
    private String author;
    private int published_year;
    private int available_copies;
}
