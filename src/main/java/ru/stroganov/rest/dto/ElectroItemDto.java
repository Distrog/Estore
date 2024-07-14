package ru.stroganov.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stroganov.dao.entity.ElectroTypeEntity;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сущность товара")
public class ElectroItemDto {

    @Schema(description = "Идентификатор")
    @NotNull
    private Long id;

    @Schema(description = "Название")
    @NotNull
    private String name;

    @Schema(description = "Тип электроники")
    @NotNull
    private ElectroTypeEntity etype;

    @Schema(description = "Стоимость")
    @NotNull
    private Long price;

    @Schema(description = "Колличество")
    @NotNull
    private Integer count;

    @Schema(description = "Наличие")
    @NotNull
    private Boolean archive;

    @Schema(description = "Описание")
    @NotNull
    private String description;
}
