package com.service.accesspointv2.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute != null && attribute ? "activo" : "inactivo";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "activo".equalsIgnoreCase(dbData);
    }
}
