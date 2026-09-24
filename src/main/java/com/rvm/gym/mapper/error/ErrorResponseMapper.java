package com.rvm.gym.mapper.error;

import com.rvm.gym.dto.error.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ErrorResponseMapper {

    @Mapping(target = "messages", expression = "java(java.util.List.of(entityNotFoundException.getMessage()))")
    ErrorResponseDto toDTO(EntityNotFoundException entityNotFoundException);
}
