package ru.stroganov.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroganov.dao.entity.PurchaseEntity;
import ru.stroganov.rest.dto.PurchaseDto;
import ru.stroganov.rest.factories.PurchaseDtoFactory;
import ru.stroganov.rest.service.PurchaseService;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/purchases")
@Tag(name = "Покупки", description = "Контроллер для выполнения операций над покупками")
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final PurchaseDtoFactory factory;

    @Operation(
            summary = "Выводит покупки",
            description = "Позволяет вывести все покупки"
    )
    @GetMapping
    public ResponseEntity<Collection<PurchaseDto>> findAllPurchases(
            @RequestParam("page-number") @Parameter(description = "номер страницы") Integer pageNumber,
            @RequestParam("page-size") @Parameter(description = "количество покупок на странице") Integer pageSize
    ) {
        Collection<PurchaseEntity> purchases = purchaseService.getAllPurchases(pageNumber, pageSize);
        return ResponseEntity.ok(factory.makeListOfPurchaseDtos(purchases));
    }

    @Operation(
            summary = "Выводит покупку",
            description = "Позволяет вывести покупку под определенным id"
    )
    @GetMapping("{purchase_id}")
    public ResponseEntity<PurchaseDto> getPurchase
            (@PathVariable("purchase_id") @Parameter(description = "Идентификатор покупки") Long id) {
        PurchaseEntity purchase = purchaseService.getPurchase(id);

        return ResponseEntity.ok(factory.makePurchaseDto(purchase));
    }

    @Operation(
            summary = "Добавляет покупку",
            description = "Позволяет добавить новую покупку"
    )
    @PostMapping
    public ResponseEntity<String> createPurchase
            (@RequestParam("electro_id") @Parameter(description = "Тип электроники") Long electroId,
             @RequestParam("employee_id") @Parameter(description = "Сотрудник оформивший покупку") Long employeeId,
             @RequestParam("purchase_date") @Parameter(description = "Дата покупки") LocalDate purchaseDate,
             @RequestParam("purchase_type") @Parameter(description = "Тип покупки") String type,
             @RequestParam @Parameter(description = "Магазин где произошла покупка") String shop) {
        PurchaseEntity purchase = purchaseService.createPurchase(electroId, employeeId, purchaseDate,
                type, shop);
        return ResponseEntity.created(URI.create("http::/localhost:8080/api/purchases/"
                + purchase.getId())).build();
    }

    @Operation(
            summary = "Изменяет покупку",
            description = "Позволяет изменить полностью покупку"
    )
    @PutMapping
    public ResponseEntity<PurchaseDto> editPurchase
            (@RequestParam @Parameter(description = "Идентификатор покупки") Long id,
             @RequestParam("electro_id") @Parameter(description = "Тип электроники") Long electroId,
             @RequestParam("employee_id") @Parameter(description = "Сотрудник оформивший покупку") Long employeeId,
             @RequestParam("purchase_date") @Parameter(description = "Дата покупки") LocalDate purchaseDate,
             @RequestParam("purchase_type") @Parameter(description = "Тип покупки") String type,
             @RequestParam @Parameter(description = "Магазин где произошла покупка") String shop) {
        PurchaseEntity purchase = purchaseService.editPurchase(id, electroId, employeeId, purchaseDate,
                type, shop);

        return ResponseEntity.ok(factory.makePurchaseDto(purchase));
    }

    @Operation(
            summary = "Изменяет покупку",
            description = "Позволяет изменить частично покупку"
    )
    @PatchMapping
    public ResponseEntity<PurchaseDto> updatePurchase
            (@RequestParam @Parameter(description = "Идентификатор покупки") Long id,
             @RequestParam(name = "electro_id", required = false) @Parameter(description = "Тип электроники") Long electroId,
             @RequestParam(name = "employee_id", required = false) @Parameter(description = "Сотрудник оформивший покупку") Long employeeId,
             @RequestParam(name = "purchase_date", required = false) @Parameter(description = "Дата покупки") LocalDate purchaseDate,
             @RequestParam(name = "purchase_type", required = false) @Parameter(description = "Тип покупки") String type,
             @RequestParam(required = false) @Parameter(description = "Магазин где произошла покупка") String shop) {
        PurchaseEntity purchase = purchaseService.updatePurchase(id, electroId, employeeId, purchaseDate,
                type, shop);

        return ResponseEntity.ok(factory.makePurchaseDto(purchase));
    }

    @Operation(
            summary = "Удаляет покупку",
            description = "Позволяет удалить покупку под определенным id"
    )
    @DeleteMapping("{purchase_id}")
    public ResponseEntity<Void> deletePurchase
            (@PathVariable("purchase_id") @Parameter(description = "Идентификатор покупки") Long id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.ok().build();
    }
}
