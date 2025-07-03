package com.djw.DemaLibrary.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowingDTO {
    String title;
    String author;
    String borrowed_at;
    String return_date;
    String returned_at;
}
