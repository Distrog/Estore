package ru.stroganov.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stroganov.dao.entity.ShopEntity;
import ru.stroganov.rest.dto.ShopDto;
import ru.stroganov.rest.factories.ShopDtoFactory;
import ru.stroganov.rest.service.ShopService;

import java.net.URI;
import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/shops")
@Tag(name = "Магазины", description = "Контроллер для выполнения операций над магазинами")
public class ShopController {
    private final ShopService shopService;
    private final ShopDtoFactory factory;

    @Operation(
            summary = "Выводит магазины",
            description = "Позволяет вывести все магазины"
    )
    @GetMapping
    public ResponseEntity<Collection<ShopDto>> getAllShops(
            @RequestParam("page-number") @Parameter(description = "номер страницы") Integer pageNumber,
            @RequestParam("page-size") @Parameter(description = "количество магазинов на странице") Integer pageSize
    ) {
        Collection<ShopEntity> shops = shopService.getAllShops(pageNumber, pageSize);
        return ResponseEntity.ok(factory.makeListOfShops(shops));
    }

    @Operation(
            summary = "Выводит магазин",
            description = "Позволяет вывести магазин под определенным id"
    )
    @GetMapping("{shop_id}")
    public ResponseEntity<ShopDto> getShop(
            @PathVariable("shop_id") @Parameter(description = "Идентификатор магазина") Long id) {
        ShopEntity shop = shopService.getShop(id);

        return ResponseEntity.ok(factory.makeShopDto(shop));
    }

    @Operation(
            summary = "Добавляет магазин",
            description = "Позволяет добавить магазин"
    )
    @PostMapping
    public ResponseEntity<String> createShop(
            @RequestParam @Parameter(description = "Название магазина") String name,
            @RequestParam @Parameter(description = "Адрес магазина") String address) {
        ShopEntity shop = shopService.createShop(name, address);

        return ResponseEntity.created(URI.create("http::/localhost:8080/api/shops/"
                + shop.getId())).build();
    }

    @Operation(
            summary = "Изменяет магазин",
            description = "Позволяет полностью изменить магазин"
    )
    @PutMapping
    public ResponseEntity<ShopDto> editShop(
            @RequestParam @Parameter(description = "Идентификатор магазина") Long id,
            @RequestParam @Parameter(description = "Название магазина") String name,
            @RequestParam @Parameter(description = "Адрес магазина") String address) {
        ShopEntity shop = shopService.editIShop(id, name, address);

        return ResponseEntity.ok(factory.makeShopDto(shop));
    }

    @Operation(
            summary = "Изменяет магазин",
            description = "Позволяет частично изменить магазин"
    )
    @PatchMapping
    public ResponseEntity<ShopDto> updateShop(
            @RequestParam @Parameter(description = "Идентификатор магазина") Long id,
            @RequestParam(required = false) @Parameter(description = "Название магазина") String name,
            @RequestParam(required = false) @Parameter(description = "Адрес магазина") String address) {
        ShopEntity shop = shopService.updateShop(id, name, address);

        return ResponseEntity.ok(factory.makeShopDto(shop));
    }

    @Operation(
            summary = "Удаляет магазин",
            description = "Позволяет удалить магазин под определенным id"
    )
    @DeleteMapping("{shop_id}")
    public ResponseEntity<Void> DeleteShop(
            @PathVariable("shop_id") @Parameter(description = "Идентификатор магазина") Long id) {
        shopService.deleteShop(id);
        return ResponseEntity.ok().build();
    }
}
