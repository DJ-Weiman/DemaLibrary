package com.djw.DemaLibrary.mappers.impl;

import com.djw.DemaLibrary.domain.dto.BookDto;
import com.djw.DemaLibrary.domain.entities.BookEntity;
import com.djw.DemaLibrary.mappers.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper implements Mapper<BookEntity, BookDto> {

    private final ModelMapper modelMapper;

    @Override
    public BookDto mapTo(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity mapFrom(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
