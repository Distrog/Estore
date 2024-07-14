package ru.stroganov.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroganov.dao.entity.ElectroItemEntity;
import ru.stroganov.rest.dto.ElectroItemDto;
import ru.stroganov.rest.factories.ElectroItemDtoFactory;
import ru.stroganov.rest.service.ElectroItemService;

import java.net.URI;
import java.util.Collection;

@RequiredArgsConstructor
@RestController("api/electro_items")
@Tag(name = "Товары", description = "Контроллер для выполнения операций над товарами")
public class ElectroItemController {

    private final ElectroItemService electroItemService;
    private final ElectroItemDtoFactory factory;

    @Operation(
            summary = "Выводит все товары",
            description = "Позволяет вывести все товары"
    )
    @GetMapping
    public ResponseEntity<Collection<ElectroItemDto>> getAllElectroItems(
            @RequestParam("page-number") @Parameter(description = "номер страницы") Integer pageNumber,
            @RequestParam("page-size") @Parameter(description = "количество товаров на странице") Integer pageSize) {
        Collection<ElectroItemEntity> electroItems = electroItemService.getAllElectroItems(pageNumber, pageSize);

        return ResponseEntity.ok(factory.makeListOfElectroItemDto(electroItems));
    }

    @Operation(
            summary = "Выводит товар",
            description = "Позволяет вывести товар под конкретным id"
    )
    @GetMapping("{electro_item_id}")
    public ResponseEntity<ElectroItemDto> getElectroItem(
            @PathVariable("electro_item_id") @Parameter(description = "Идентификатор товара") Long id) {
        ElectroItemEntity electroItem = electroItemService.getElectroItem(id);

        return ResponseEntity.ok(factory.makeElectroItemDto(electroItem));
    }

    @Operation(
            summary = "Добавляет товар",
            description = "Позволяет добавить новый товар"
    )
    @PostMapping
    public ResponseEntity<String> createElectroItem
            (@RequestParam @Parameter(description = "Название товара") String name,
             @RequestParam @Parameter(description = "Тип товара") String etype,
             @RequestParam @Parameter(description = "Цена товара") Long price,
             @RequestParam @Parameter(description = "Колличество товара") Integer count,
             @RequestParam @Parameter(description = "Наличие товара") Boolean archive,
             @RequestParam @Parameter(description = "Описание товара") String description) {
        ElectroItemEntity electroItem = electroItemService.createElectroItem(name, etype, price, count,
                archive, description);

        return ResponseEntity.created(URI.create("http::/localhost:8080/api/electro_items/"
                + electroItem.getId())).build();
    }

    @Operation(
            summary = "Изменяет товар",
            description = "Позволяет изменить полностью вест товар"
    )
    @PutMapping
    public ResponseEntity<ElectroItemDto> editElectroItem(
            @RequestParam @Parameter(description = "Идентификатор товара") Long id,
            @RequestParam @Parameter(description = "Название товара") String name,
            @RequestParam @Parameter(description = "Тип товара") String etype,
            @RequestParam @Parameter(description = "Цена товара") Long price,
            @RequestParam @Parameter(description = "Колличество товара") Integer count,
            @RequestParam @Parameter(description = "Наличие товара") Boolean archive,
            @RequestParam @Parameter(description = "Описание товара") String description) {
        ElectroItemEntity electroItem = electroItemService.editElectroItem(id, name, etype, price, count,
                archive, description);

        return ResponseEntity.ok(factory.makeElectroItemDto(electroItem));
    }

    @Operation(
            summary = "Изменяет товар частично",
            description = "Позволяет частично изменить товар"
    )
    @PatchMapping
    public ResponseEntity<ElectroItemDto> updateElectroitem(
            @RequestParam(required = false) @Parameter(description = "Идентификатор товара") Long id,
            @RequestParam(required = false) @Parameter(description = "Название товара") String name,
            @RequestParam(required = false) @Parameter(description = "Тип товара") String etype,
            @RequestParam(required = false) @Parameter(description = "Цена товара") Long price,
            @RequestParam(required = false) @Parameter(description = "Колличество товара") Integer count,
            @RequestParam(required = false) @Parameter(description = "Наличие товара") Boolean archive,
            @RequestParam(required = false) @Parameter(description = "Описание товара") String description) {
        ElectroItemEntity electroItem = electroItemService.updateElectroitem(id, name, etype, price, count,
                archive, description);

        return ResponseEntity.ok(factory.makeElectroItemDto(electroItem));
    }

    @Operation(
            summary = "Удаляет товар",
            description = "Позволяет удалить товар под канкретным id"
    )
    @DeleteMapping("electro_item_id")
    public ResponseEntity<Void> deleteElectroItem(
            @PathVariable("electro_item_id") @Parameter(description = "Идентификатор товара") Long id) {
        electroItemService.deleteElectroItem(id);
        return ResponseEntity.ok().build();
    }
}
