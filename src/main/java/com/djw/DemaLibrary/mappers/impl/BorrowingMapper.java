package com.djw.DemaLibrary.mappers.impl;

import com.djw.DemaLibrary.domain.dto.BorrowingRequest;
import com.djw.DemaLibrary.domain.entities.BorrowingEntity;
import com.djw.DemaLibrary.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BorrowingMapper implements Mapper<BorrowingEntity, BorrowingRequest> {

    private final ModelMapper modelMapper;

    public BorrowingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BorrowingRequest mapTo(BorrowingEntity borrowingEntity) {
        return modelMapper.map(borrowingEntity, BorrowingRequest.class);
    }

    @Override
    public BorrowingEntity mapFrom(BorrowingRequest borrowingDto) {
        return modelMapper.map(borrowingDto, BorrowingEntity.class);
    }
}
