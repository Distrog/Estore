package ru.stroganov.rest.factories;

import org.springframework.stereotype.Component;
import ru.stroganov.dao.entity.ElectroItemEntity;
import ru.stroganov.rest.dto.ElectroItemDto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElectroItemDtoFactory {

    public ElectroItemDto makeElectroItemDto(ElectroItemEntity entity) {
        return ElectroItemDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .etype(entity.getEtype())
                .price(entity.getPrice())
                .count(entity.getCount())
                .description(entity.getDescription())
                .archive(entity.getArchive())
                .build();
    }

    public Collection<ElectroItemDto> makeListOfElectroItemDto(Collection<ElectroItemEntity> entities){
        return entities.stream()
                .map(this::makeElectroItemDto)
                .collect(Collectors.toList());
    }

}
