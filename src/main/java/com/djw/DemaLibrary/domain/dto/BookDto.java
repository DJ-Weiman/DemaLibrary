package com.djw.DemaLibrary.domain.dto;

import com.djw.DemaLibrary.validation.PastOrPresentYear;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Please provide a valid title for Book")
    private String title;

    @NotBlank(message = "Please provide a valid author for Book")
    private String author;

    private String genre;

    private String coverUrl;

    private float rating;

    private String summary;

    @NotNull(message = "Please provide a valid published year")
    @Min(value = 0, message = "Published year has to be greater than 0")
    @PastOrPresentYear
    private int published_year;

    @NotNull(message = "Min copies cannot be null")
    @Min(value = 0, message = "Min available copies cannot be less than zero")
    private int total_copies;

}
