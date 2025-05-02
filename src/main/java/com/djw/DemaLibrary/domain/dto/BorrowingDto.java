package com.djw.DemaLibrary.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingDto {
    private UUID id;
    private UUID user_id;
    private UUID book_id;
    private LocalDateTime borrowed_at;
    private LocalDateTime returned_at;
}
