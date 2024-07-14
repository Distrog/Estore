package ru.stroganov.rest.factories;

import org.springframework.stereotype.Component;
import ru.stroganov.dao.entity.PurchaseEntity;
import ru.stroganov.rest.dto.PurchaseDto;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class PurchaseDtoFactory {

    public PurchaseDto makePurchaseDto(PurchaseEntity entity){
        return PurchaseDto.builder()
                .id(entity.getId())
                .electro(entity.getElectro())
                .employee(entity.getEmployee())
                .purchaseDate(entity.getPurchaseDate())
                .type(entity.getType())
                .shop(entity.getShop())
                .build();
    }

    public Collection<PurchaseDto> makeListOfPurchaseDtos(Collection<PurchaseEntity> entities){
        return entities.stream().map(this::makePurchaseDto).collect(Collectors.toList());
    }
}
