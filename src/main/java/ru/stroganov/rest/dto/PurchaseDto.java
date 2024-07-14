package ru.stroganov.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stroganov.dao.entity.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сущность покупки")
public class PurchaseDto {
    @Schema(description = "Идентификатор")
    @NotNull
    private Long id;

    @Schema(description = "Тип электроники")
    @NotNull
    private ElectroItemEntity electro;

    @Schema(description = "Работник который произвел продажу")
    @NotNull
    private EmployeeEntity employee;

    @Schema(description = "Дата", example = "2024-01-01")
    @NotNull
    @JsonProperty("purchase_date")
    private LocalDate purchaseDate;

    @Schema(description = "Тип покупки")
    @NotNull
    private PurchaseTypeEntity type;

    @Schema(description = "Магазин")
    @NotNull
    private ShopEntity shop;
}
