package ru.stroganov.rest.factories;

import org.springframework.stereotype.Component;
import ru.stroganov.dao.entity.PurchaseTypeEntity;
import ru.stroganov.rest.dto.PurchaseTypeDto;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class PurchaseTypeDtoFactory {

    public PurchaseTypeDto makePurchaseTypeDto(PurchaseTypeEntity purchaseTypeEntity) {
        return PurchaseTypeDto.builder()
                .id(purchaseTypeEntity.getId())
                .name(purchaseTypeEntity.getName())
                .build();
    }

    public Collection<PurchaseTypeDto> makeListOfPositionTypeDtos(Collection<PurchaseTypeEntity> entities){
        return entities.stream().map(this::makePurchaseTypeDto).collect(Collectors.toList());
    }

}
