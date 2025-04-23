package com.djw.DemaLibrary.mappers.impl;

import com.djw.DemaLibrary.domain.dto.BorrowingDto;
import com.djw.DemaLibrary.domain.entities.BorrowingEntity;
import com.djw.DemaLibrary.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BorrowingMapper implements Mapper<BorrowingEntity, BorrowingDto> {

    private final ModelMapper modelMapper;

    public BorrowingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BorrowingDto mapTo(BorrowingEntity borrowingEntity) {
        return modelMapper.map(borrowingEntity, BorrowingDto.class);
    }

    @Override
    public BorrowingEntity mapFrom(BorrowingDto borrowingDto) {
        return modelMapper.map(borrowingDto, BorrowingEntity.class);
    }
}
