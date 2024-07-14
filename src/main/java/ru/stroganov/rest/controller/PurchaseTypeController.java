package ru.stroganov.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroganov.dao.entity.PurchaseTypeEntity;
import ru.stroganov.rest.dto.PurchaseTypeDto;
import ru.stroganov.rest.factories.PurchaseTypeDtoFactory;
import ru.stroganov.rest.service.PurchaseTypeService;

import java.net.URI;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/purchase_types")
@Tag(name = "Типы покупок", description = "Контроллер для выполнения операций над типами покупок")
public class PurchaseTypeController {
    private final PurchaseTypeService purchaseTypeService;
    private final PurchaseTypeDtoFactory factory;

    @Operation(
            summary = "Выводит типы покупок",
            description = "Позволяет вывести все типы покупок"
    )
    @GetMapping
    public ResponseEntity<Collection<PurchaseTypeDto>> getAllPurchaseTypes(
            @RequestParam("page-number") @Parameter(description = "номер страницы") Integer pageNumber,
            @RequestParam("page-size") @Parameter(description = "количество типов покупок на странице") Integer pageSize
    ) {
        Collection<PurchaseTypeEntity> purchaseTypeEntities = purchaseTypeService.getAllPurchaseTypes(pageNumber, pageSize);
        return ResponseEntity.ok(factory.makeListOfPositionTypeDtos(purchaseTypeEntities));
    }

    @Operation(
            summary = "Выводит тип покупки",
            description = "Позволяет вывести тип покупки под определенным id"
    )
    @GetMapping("{purchase_type_id}")
    public ResponseEntity<PurchaseTypeDto> getPurchaseType
            (@PathVariable("purchase_type_id") @Parameter(description = "Идентификатор типа покупки") Long id) {
        PurchaseTypeEntity purchaseType = purchaseTypeService.getPurchaseType(id);

        return ResponseEntity.ok(factory.makePurchaseTypeDto(purchaseType));
    }

    @Operation(
            summary = "Добавляет тип покупки",
            description = "Позволяет добавить тип покупки"
    )
    @PostMapping
    public ResponseEntity<String> createPurchaseType
            (@RequestParam String name) {
        PurchaseTypeEntity purchaseType = purchaseTypeService.createPurchaseType(name);

        return ResponseEntity.created(URI.create("http::/localhost:8080/api/purchase_types/"
                + purchaseType.getId())).build();
    }

    @Operation(
            summary = "Изменяет тип покупки",
            description = "Позволяет изменить полностью тип покупки"
    )
    @PutMapping
    public ResponseEntity<PurchaseTypeDto> editPurchaseType
            (@RequestParam @Parameter(description = "Идентификатор типа покупки") Long id,
             @RequestParam @Parameter(description = "Название типа покупки") String name) {
        PurchaseTypeEntity purchaseType = purchaseTypeService.editPurchaseType(id, name);

        return ResponseEntity.ok(factory.makePurchaseTypeDto(purchaseType));
    }

    @Operation(
            summary = "Удаляет тип покупки",
            description = "Позволяет удалить тип покупки под определенным id"
    )
    @DeleteMapping("{purchase_type_id}")
    public ResponseEntity<Void> deletePurchaseType
            (@PathVariable("purchase_type_id") @Parameter(description = "Идентификатор типа покупки") Long id) {
        purchaseTypeService.deletePurchaseType(id);
        return ResponseEntity.ok().build();
    }
}
