package ru.stroganov.rest.factories;

import org.springframework.stereotype.Component;
import ru.stroganov.dao.entity.PositionTypeEntity;
import ru.stroganov.rest.dto.PositionTypeDto;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class PositionTypeDtoFactory {
    public PositionTypeDto makePositionTypeDto(PositionTypeEntity positionTypeEntity){
        return PositionTypeDto.builder()
                .id(positionTypeEntity.getId())
                .name(positionTypeEntity.getName())
                .build();
    }

    public Collection<PositionTypeDto> makeListOfPositionTypes(Collection<PositionTypeEntity> entities){
        return entities.stream().map(this::makePositionTypeDto).collect(Collectors.toList());
    }
}
