package com.rvm.gym.enums.converter;

import com.rvm.gym.enums.EstimuloEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class EstimuloConverter implements AttributeConverter<EstimuloEnum, UUID> {

    @Override
    public UUID convertToDatabaseColumn(EstimuloEnum estimuloEnum) {
        return estimuloEnum != null ? estimuloEnum.getId() : null;
    }

    @Override
    public EstimuloEnum convertToEntityAttribute(UUID id) {
        return id != null ? EstimuloEnum.fromId(id) : null;
    }
}
