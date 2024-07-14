package ru.stroganov.rest.service;

import ru.stroganov.dao.entity.PositionTypeEntity;

import java.util.List;

public interface PositionTypeService {
    List<PositionTypeEntity> getAllPositionTypes(Integer pageNumber, Integer pageSize);
    PositionTypeEntity getPositionTypeByName(String positionTypeName);

    PositionTypeEntity getPositionType(Long id);

    PositionTypeEntity createPositiontype(String name);

    PositionTypeEntity editPositionType(Long id, String name);

    void deletePositiontype(Long id);
}
