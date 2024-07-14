package ru.stroganov.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.stroganov.dao.entity.PositionTypeEntity;
import ru.stroganov.dao.entity.ShopEntity;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сущность сотрудника")
public class EmployeeDto {

    @Schema(description = "Идентификатор")
    @NotNull
    private Long id;

    @Schema(description = "Фамилия")
    @NotNull
    @JsonProperty("lastname")
    private String lastName;

    @Schema(description = "Имя")
    @NotNull
    @JsonProperty("firstname")
    private String firstName;

    @Schema(description = "Отчество")
    @NotNull
    private String patronymic;

    @Schema(description = "Дата рождения", example = "2024-01-01")
    @NotNull
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @Schema(description = "Профессия")
    @NotNull
    private PositionTypeEntity position;

    @Schema(description = "Магазин")
    @NotNull
    private ShopEntity shop;

    @Schema(description = "Пол",example = "m")
    @NotNull
    private Boolean gender;
}
