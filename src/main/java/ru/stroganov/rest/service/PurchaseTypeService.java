package ru.stroganov.rest.service;

import ru.stroganov.dao.entity.PurchaseTypeEntity;

import java.util.Collection;

public interface PurchaseTypeService {
    Collection<PurchaseTypeEntity> getAllPurchaseTypes(Integer pageNumber, Integer pageSize);

    PurchaseTypeEntity getPurchaseType(Long id);

    PurchaseTypeEntity createPurchaseType(String name);

    PurchaseTypeEntity editPurchaseType(Long id, String name);

    PurchaseTypeEntity getPurchaseTypeByName(String name);

    void deletePurchaseType(Long id);
}
