package com.djw.DemaLibrary.config;

import com.djw.DemaLibrary.domain.dto.BorrowingDTO;
import com.djw.DemaLibrary.domain.entities.BorrowingEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BorrowingMapperConfig {
    private final ModelMapper modelMapper;

    @PostConstruct
    public void configureBorrowingMapping(){
        modelMapper.createTypeMap(BorrowingEntity.class, BorrowingDTO.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getBook().getTitle(), BorrowingDTO::setTitle);
                    mapper.map(src -> src.getBook().getAuthor(), BorrowingDTO::setAuthor);
                    mapper.map(BorrowingEntity::getBorrowed_at, BorrowingDTO::setBorrowed_at);
                    mapper.map(BorrowingEntity::getReturnDate, BorrowingDTO::setReturn_date);
                    mapper.map(BorrowingEntity::getReturned_at, BorrowingDTO::setReturned_at);
                });
    }
}
