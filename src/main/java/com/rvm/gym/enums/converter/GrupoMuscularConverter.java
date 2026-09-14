package com.rvm.gym.enums.converter;

import com.rvm.gym.enums.GrupoMuscularEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class GrupoMuscularConverter implements AttributeConverter<GrupoMuscularEnum, UUID> {

    @Override
    public UUID convertToDatabaseColumn(GrupoMuscularEnum grupoMuscularEnum) {
        return grupoMuscularEnum != null ? grupoMuscularEnum.getId() : null;
    }

    @Override
    public GrupoMuscularEnum convertToEntityAttribute(UUID id) {
        return id != null ? GrupoMuscularEnum.fromId(id) : null;
    }
}
