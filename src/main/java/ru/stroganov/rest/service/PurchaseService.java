package ru.stroganov.rest.service;

import ru.stroganov.dao.entity.PurchaseEntity;

import java.time.LocalDate;
import java.util.Collection;

public interface PurchaseService {
    Collection<PurchaseEntity> getAllPurchases(Integer pageNumber, Integer pageSize);

    PurchaseEntity getPurchase(Long id);

    PurchaseEntity createPurchase(Long electroId, Long employeeId, LocalDate purchaseDate,
                                  String type, String shop);

    PurchaseEntity editPurchase(Long id, Long electroId, Long employeeId, LocalDate purchaseDate,
                                String type, String shop);

    PurchaseEntity updatePurchase(Long id, Long electroId, Long employeeId, LocalDate purchaseDate,
                                  String type, String shop);

    void deletePurchase(Long id);
}
