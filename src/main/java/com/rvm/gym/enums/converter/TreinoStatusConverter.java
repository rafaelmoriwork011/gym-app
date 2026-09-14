package com.rvm.gym.enums.converter;

import com.rvm.gym.enums.TreinoStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class TreinoStatusConverter implements AttributeConverter<TreinoStatusEnum, UUID> {

    @Override
    public UUID convertToDatabaseColumn(TreinoStatusEnum treinoStatusEnum) {
        return treinoStatusEnum != null ? treinoStatusEnum.getId() : null;
    }

    @Override
    public TreinoStatusEnum convertToEntityAttribute(UUID id) {
        return id != null ? TreinoStatusEnum.fromId(id) : null;
    }
}
