package com.djw.DemaLibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingResponse {
    private String status;
    private String bookTitle;
    private String authorName;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnDate;
}
