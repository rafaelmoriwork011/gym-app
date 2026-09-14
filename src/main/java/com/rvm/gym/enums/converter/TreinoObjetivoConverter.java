package com.rvm.gym.enums.converter;

import com.rvm.gym.enums.TreinoObjetivoEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class TreinoObjetivoConverter implements AttributeConverter<TreinoObjetivoEnum, UUID> {

    @Override
    public UUID convertToDatabaseColumn(TreinoObjetivoEnum attribute) {
        return attribute != null ? attribute.getId() : null;
    }

    @Override
    public TreinoObjetivoEnum convertToEntityAttribute(UUID id) {
        return id != null ? TreinoObjetivoEnum.fromId(id) : null;
    }
}