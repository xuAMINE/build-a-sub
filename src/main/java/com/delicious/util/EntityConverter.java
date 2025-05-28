package com.delicious.util;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityConverter<E, D> {
    private final Function<E, D> toDto;
    private final Function<D, E> toEntity;

    public EntityConverter(Function<E, D> toDto, Function<D, E> toEntity) {
        this.toDto = toDto;
        this.toEntity = toEntity;
    }

    public D convertToDto(E entity) {
        return Optional.ofNullable(entity)
                .map(toDto)
                .orElse(null);
    }

    public E convertToEntity(D dto) {
        return Optional.ofNullable(dto)
                .map(toEntity)
                .orElse(null);
    }

    public List<D> convertToDtoList(List<E> entities) {
        return entities.stream()
                .map(toDto)
                .collect(Collectors.toList());
    }

    public List<E> convertToEntityList(List<D> dtos) {
        return dtos.stream()
                .map(toEntity)
                .collect(Collectors.toList());
    }
}