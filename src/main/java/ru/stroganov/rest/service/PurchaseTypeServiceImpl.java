package ru.stroganov.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.stroganov.dao.entity.PurchaseTypeEntity;
import ru.stroganov.dao.repository.PurchaseTypeRepository;
import ru.stroganov.exception.NotFoundPurchaseTypeException;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class PurchaseTypeServiceImpl implements PurchaseTypeService {
    private final PurchaseTypeRepository purchaseTypeRepository;

    @Override
    public Collection<PurchaseTypeEntity> getAllPurchaseTypes(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return purchaseTypeRepository.findAll(pageRequest).getContent();
    }

    @Override
    public PurchaseTypeEntity getPurchaseType(Long id) {
        return purchaseTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundPurchaseTypeException(String
                        .format("не найден тип покупок №%d", id)));
    }

    @Override
    public PurchaseTypeEntity createPurchaseType(String name) {
        PurchaseTypeEntity purchaseType = PurchaseTypeEntity.builder()
                .name(name).build();
        return purchaseTypeRepository.save(purchaseType);
    }

    @Override
    public PurchaseTypeEntity editPurchaseType(Long id, String name) {
        PurchaseTypeEntity purchaseType = getPurchaseType(id);
        purchaseType.setName(name);

        return purchaseTypeRepository.save(purchaseType);
    }

    @Override
    public PurchaseTypeEntity getPurchaseTypeByName(String name) {
        return purchaseTypeRepository.findByName(name)
                .orElseThrow(() -> new NotFoundPurchaseTypeException(String
                        .format("не найден тип покупок \"%s\"", name)));
    }

    @Override
    public void deletePurchaseType(Long id) {
        PurchaseTypeEntity purchaseType = getPurchaseType(id);
        purchaseTypeRepository.delete(purchaseType);
    }
}
