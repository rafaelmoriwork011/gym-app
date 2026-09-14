package com.rvm.gym.enums.converter;

import com.rvm.gym.enums.TreinoConfiguracaoStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class TreinoConfiguracaoStatusConverter implements AttributeConverter<TreinoConfiguracaoStatusEnum, UUID> {

    @Override
    public UUID convertToDatabaseColumn(TreinoConfiguracaoStatusEnum attribute) {
        return attribute != null ? attribute.getId() : null;
    }

    @Override
    public TreinoConfiguracaoStatusEnum convertToEntityAttribute(UUID id) {
        return id != null ? TreinoConfiguracaoStatusEnum.fromId(id) : null;
    }
}