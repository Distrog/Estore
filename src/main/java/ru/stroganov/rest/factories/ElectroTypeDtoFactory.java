package ru.stroganov.rest.factories;

import org.springframework.stereotype.Component;
import ru.stroganov.dao.entity.ElectroTypeEntity;
import ru.stroganov.rest.dto.ElectroTypeDto;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ElectroTypeDtoFactory {
    public ElectroTypeDto makeElectroTypeDto(ElectroTypeEntity electroTypeEntity){
        return ElectroTypeDto.builder()
                .id(electroTypeEntity.getId())
                .name(electroTypeEntity.getName())
                .build();
    }

    public Collection<ElectroTypeDto> makeListOfElectroTypeDtos(Collection<ElectroTypeEntity> entities){
        return entities.stream().map(this::makeElectroTypeDto).collect(Collectors.toList());
    }
}
