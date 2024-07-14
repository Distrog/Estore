package ru.stroganov.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.stroganov.dao.entity.PurchaseEntity;
import ru.stroganov.dao.repository.*;
import ru.stroganov.exception.NotFoundPurchaseException;

import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ElectroItemService electroItemService;
    private final EmployeeService employeeService;
    private final PurchaseTypeService purchaseTypeService;
    private final ShopService shopService;

    @Override
    public Collection<PurchaseEntity> getAllPurchases(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return purchaseRepository.findAll(pageRequest).getContent();
    }

    @Override
    public PurchaseEntity getPurchase(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new NotFoundPurchaseException(String
                        .format("не найдена покупка №%d", id)));
    }

    @Override
    public PurchaseEntity createPurchase(Long electroId, Long employeeId, LocalDate purchaseDate,
                                         String type, String shop) {
        PurchaseEntity purchase = PurchaseEntity.builder()
                .electro(electroItemService.getElectroItem(electroId))
                .employee(employeeService.getEmployee(employeeId))
                .purchaseDate(purchaseDate)
                .type(purchaseTypeService.getPurchaseTypeByName(type))
                .shop(shopService.getShopByName(shop))
                .build();

        return purchaseRepository.save(purchase);
    }

    @Override
    public PurchaseEntity editPurchase(Long id, Long electroId, Long employeeId,
                                      LocalDate purchaseDate, String type, String shop) {
        PurchaseEntity purchase = getPurchase(id);

        purchase.setElectro(electroItemService.getElectroItem(electroId));
        purchase.setEmployee(employeeService.getEmployee(employeeId));
        purchase.setPurchaseDate(purchaseDate);
        purchase.setType(purchaseTypeService.getPurchaseTypeByName(type));
        purchase.setShop(shopService.getShopByName(shop));

        return purchaseRepository.save(purchase);
    }

    @Override
    public PurchaseEntity updatePurchase(Long id, Long electroId, Long employeeId,
                                         LocalDate purchaseDate, String type, String shop) {
        PurchaseEntity purchase = getPurchase(id);

        if (electroId != null) {
            purchase.setElectro(electroItemService.getElectroItem(electroId));
        }
        if (employeeId != null) {
            purchase.setEmployee(employeeService.getEmployee(employeeId));
        }
        if (purchaseDate != null) {
            purchase.setPurchaseDate(purchaseDate);
        }
        if (type != null) {
            purchase.setType(purchaseTypeService.getPurchaseTypeByName(type));
        }
        if (shop != null) {
            purchase.setShop(shopService.getShopByName(shop));
        }

        return purchaseRepository.save(purchase);
    }

    @Override
    public void deletePurchase(Long id) {
        PurchaseEntity purchase = getPurchase(id);
        purchaseRepository.delete(purchase);
    }
}
