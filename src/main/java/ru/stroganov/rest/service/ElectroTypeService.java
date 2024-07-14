package ru.stroganov.rest.service;

import ru.stroganov.dao.entity.ElectroTypeEntity;

import java.util.Collection;

public interface ElectroTypeService {
    Collection<ElectroTypeEntity> getAllElectroTypes(Integer pageNumber, Integer pageSize);

    ElectroTypeEntity getElectoType(Long id);

   ElectroTypeEntity getElectroTypeByName(String etypeName);

    ElectroTypeEntity createElectroType(String name);

    ElectroTypeEntity editElectroService(Long id, String name);

    void deleteElectroType(Long id);
}
