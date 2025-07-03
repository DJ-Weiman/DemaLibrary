package com.djw.DemaLibrary.mappers.impl;

import com.djw.DemaLibrary.domain.dto.BorrowingDTO;
import com.djw.DemaLibrary.domain.dto.BorrowingRequest;
import com.djw.DemaLibrary.domain.entities.BorrowingEntity;
import com.djw.DemaLibrary.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BorrowingMapper implements Mapper<BorrowingEntity, BorrowingDTO> {

    private final ModelMapper modelMapper;

    public BorrowingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BorrowingDTO mapTo(BorrowingEntity borrowingEntity) {
        return modelMapper.map(borrowingEntity, BorrowingDTO.class);
    }

    @Override
    public BorrowingEntity mapFrom(BorrowingDTO borrowingDTO) {
        return modelMapper.map(borrowingDTO, BorrowingEntity.class);
    }
}
