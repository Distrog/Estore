package ru.stroganov.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroganov.dao.entity.ElectroTypeEntity;
import ru.stroganov.rest.dto.ElectroTypeDto;
import ru.stroganov.rest.factories.ElectroTypeDtoFactory;
import ru.stroganov.rest.service.ElectroTypeService;

import java.net.URI;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/electro_items")
@Tag(name = "Типы электроники", description = "Контроллер для выполнения операций над типами электроники")
public class ElectroTypeController {

    private final ElectroTypeService electroTypeService;
    private final ElectroTypeDtoFactory factory;

    @Operation(
            summary = "Выводит все типы электроники",
            description = "Позволяет вывести все товары"
    )
    @GetMapping
    public ResponseEntity<Collection<ElectroTypeDto>> getAllElectroTypes(
            @RequestParam("page-number") @Parameter(description = "номер страницы") Integer pageNumber,
            @RequestParam("page-size") @Parameter(description = "количество типов электроники на странице") Integer pageSize
    ) {
        Collection<ElectroTypeEntity> entities = electroTypeService.getAllElectroTypes(pageNumber, pageSize);

        return ResponseEntity.ok(factory.makeListOfElectroTypeDtos(entities));
    }

    @Operation(
            summary = "Выводит тип электроники",
            description = "Позволяет вывести тип электроники под определенын id"
    )
    @GetMapping("{electoro_type_id}")
    public ResponseEntity<ElectroTypeDto> getElectroType(
            @PathVariable("electro_type_id") @Parameter(description = "Идентификатор типа электроники") Long id) {
        ElectroTypeEntity electroType = electroTypeService.getElectoType(id);

        return ResponseEntity.ok(factory.makeElectroTypeDto(electroType));
    }


    @Operation(
            summary = "Добавляет тип электроники",
            description = "Позволяет добивать новый тип электроники"
    )
    @PostMapping
    public ResponseEntity<String> createElectroType
            (@RequestParam @Parameter(description = "Название типа электроники") String name) {
        ElectroTypeEntity electroType = electroTypeService.createElectroType(name);

        return ResponseEntity.created(URI.create("http::/localhost:8080/api/electro_types/"
                + electroType.getId())).build();
    }

    @Operation(
            summary = "Изменяет тип электроники",
            description = "Позволяет изменить полностью один тип электроники"
    )
    @PutMapping
    public ResponseEntity<ElectroTypeDto> editElectroType
            (@RequestParam @Parameter(description = "Идентификатор типа электроники") Long id,
             @RequestParam @Parameter(description = "Название типа электроники") String name) {
        ElectroTypeEntity electroType = electroTypeService.editElectroService(id, name);

        return ResponseEntity.ok(factory.makeElectroTypeDto(electroType));
    }

    @Operation(
            summary = "Удаляет тип электроники",
            description = "Позволяет удалить тип электроники под определенным id"
    )
    @DeleteMapping("electro_type_id")
    public ResponseEntity<Void> deleteElectroType
            (@PathVariable("electro_type_id") @Parameter(description = "Идентификатор типа электроники") Long id) {
        electroTypeService.deleteElectroType(id);
        return ResponseEntity.ok().build();
    }
}
