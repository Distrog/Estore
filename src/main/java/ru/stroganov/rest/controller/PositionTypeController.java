package ru.stroganov.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroganov.dao.entity.PositionTypeEntity;
import ru.stroganov.rest.dto.PositionTypeDto;
import ru.stroganov.rest.factories.PositionTypeDtoFactory;
import ru.stroganov.rest.service.PositionTypeService;

import java.net.URI;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/position_types")
@Tag(name = "Должности", description = "Контроллер для выполнения операций над должностями сотрудников")
public class PositionTypeController {
    private final PositionTypeService positionTypeService;
    private final PositionTypeDtoFactory factory;

    @Operation(
            summary = "Выводит все профессии",
            description = "Позволяет вывести все профессии"
    )
    @GetMapping
    public ResponseEntity<Collection<PositionTypeDto>> getAllPositionTypes(
            @RequestParam("page-number") @Parameter(description = "номер страницы") Integer pageNumber,
            @RequestParam("page-size") @Parameter(description = "количество профессий на странице") Integer pageSize
    ) {
        Collection<PositionTypeEntity> postionTypes = positionTypeService.getAllPositionTypes(pageNumber, pageSize);


        return ResponseEntity.ok(factory.makeListOfPositionTypes(postionTypes));
    }

    @Operation(
            summary = "Выводит профессию",
            description = "Позволяет вывести профессию под определенным id"
    )
    @GetMapping("{position_type_id}")
    public ResponseEntity<PositionTypeDto> getPostiontype
            (@PathVariable("position_type_id") @Parameter(description = "Идентификатор профессии") Long id) {
        PositionTypeEntity positionType = positionTypeService.getPositionType(id);

        return ResponseEntity.ok(factory.makePositionTypeDto(positionType));
    }


    @Operation(
            summary = "Добавляет новую профессию",
            description = "Позволяет добавить новую профессию"
    )
    @PostMapping
    public ResponseEntity<String> createPositionType
            (@RequestParam @Parameter(description = "Название профессии") String name) {
        PositionTypeEntity positionType = positionTypeService.createPositiontype(name);

        return ResponseEntity.created(URI.create("http::/localhost:8080/api/position_types/"
                + positionType.getId())).build();
    }

    @Operation(
            summary = "Изменяет профессию",
            description = "Позволяет изменить полностью профессию"
    )
    @PutMapping
    public ResponseEntity<PositionTypeDto> editPositionType
            (@RequestParam @Parameter(description = "Идентификатор профессии") Long id,
             @RequestParam @Parameter(description = "Название профессии") String name) {
        PositionTypeEntity positionType = positionTypeService.editPositionType(id, name);
        return ResponseEntity.ok(factory.makePositionTypeDto(positionType));
    }

    @Operation(
            summary = "Удаляет профессию",
            description = "Позволяет удалить профессию под определенным id"
    )
    @DeleteMapping("{position_type_id}")
    public ResponseEntity<Void> deletePositionType
            (@PathVariable("position_type_id") @Parameter(description = "Идентификатор профессии") Long id) {
        positionTypeService.deletePositiontype(id);
        return ResponseEntity.ok().build();
    }
}
