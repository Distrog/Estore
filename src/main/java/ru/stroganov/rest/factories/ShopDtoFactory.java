package ru.stroganov.rest.factories;

import org.springframework.stereotype.Component;
import ru.stroganov.dao.entity.ShopEntity;
import ru.stroganov.rest.dto.ShopDto;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ShopDtoFactory {

    public ShopDto makeShopDto(ShopEntity shopEntity){
        return ShopDto.builder()
                .id(shopEntity.getId())
                .name(shopEntity.getName())
                .address(shopEntity.getAddress())
                .build();
    }

    public Collection<ShopDto> makeListOfShops(Collection<ShopEntity> entities){
        return entities.stream().map(this::makeShopDto).collect(Collectors.toList());
    }
}
