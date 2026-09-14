package com.rvm.gym.enums.converter;

import com.rvm.gym.enums.SexoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<SexoEnum, UUID> {

    @Override
    public UUID convertToDatabaseColumn(SexoEnum sexoEnum) {
        return sexoEnum != null ? sexoEnum.getId() : null;
    }

    @Override
    public SexoEnum convertToEntityAttribute(UUID id) {
        return id != null ? SexoEnum.fromId(id) : null;
    }
}
