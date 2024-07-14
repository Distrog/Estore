package ru.stroganov.rest.factories;

import org.springframework.stereotype.Component;
import ru.stroganov.dao.entity.EmployeeEntity;
import ru.stroganov.rest.dto.EmployeeDto;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class EmployeeDtoFactory {
    public EmployeeDto makeEmployeeDto(EmployeeEntity entity){
        return EmployeeDto.builder()
                .id(entity.getId())
                .lastName(entity.getLastName())
                .firstName(entity.getFirstName())
                .patronymic(entity.getPatronymic())
                .birthDate(entity.getBirthDate())
                .position(entity.getPosition())
                .shop(entity.getShop())
                .gender(entity.getGender())
                .build();
    }

    public Collection<EmployeeDto> makeListOfEmployees(Collection<EmployeeEntity> entities){
        return entities.stream().map(this::makeEmployeeDto).collect(Collectors.toList());
    }
}
