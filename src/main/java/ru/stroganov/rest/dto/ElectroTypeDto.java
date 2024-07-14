package ru.stroganov.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сущность типа электроники")
public class ElectroTypeDto {
    @Schema(description = "Идентификатор")
    @NotNull
    private Long id;

    @Schema(description = "Название")
    @NotNull
    private String name;
}
