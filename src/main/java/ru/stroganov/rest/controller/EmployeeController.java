package ru.stroganov.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.stroganov.dao.entity.EmployeeEntity;
import ru.stroganov.rest.dto.EmployeeDto;
import ru.stroganov.rest.factories.EmployeeDtoFactory;
import ru.stroganov.rest.service.EmployeeService;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collection;

@Transactional
@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
@Tag(name = "Сотрудники", description = "Контроллер для выполнения операций над сотрудниками")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeDtoFactory factory;

    @Operation(
            summary = "Выводит всех сотрдуников",
            description = "Позволяет вывести всех сотрудников"
    )
    @GetMapping
    public ResponseEntity<Collection<EmployeeDto>> getAllEmployees(
            @RequestParam("page-number") @Parameter(description = "номер страницы") Integer pageNumber,
            @RequestParam("page-size") @Parameter(description = "количество сотрудников на странице") Integer pageSize
    ) {
        Collection<EmployeeEntity> employees = employeeService.getAllEmployees(pageNumber, pageSize);

        return ResponseEntity.ok(factory.makeListOfEmployees(employees));
    }

    @Operation(
            summary = "Выводит сотрудника",
            description = "Позволяет вывести сотрудника под определенным id"
    )
    @GetMapping("{employee_id}")
    public ResponseEntity<EmployeeDto> getEmployee
            (@PathVariable("employee_id") @Parameter(description = "Идентификатор сотрудника") Long id) {
        EmployeeEntity employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(factory.makeEmployeeDto(employee));
    }

    @Operation(
            summary = "Добавляет сотрудника",
            description = "Позволяет добавить нового сотрудника"
    )
    @PostMapping
    public ResponseEntity<String> createEmployee
            (@RequestParam @Parameter(description = "Фамилия сотрудника") String lastname,
             @RequestParam @Parameter(description = "Имя сотрудника") String firstname,
             @RequestParam @Parameter(description = "Отчество сотрудника") String patronymic,
             @RequestParam @Parameter(description = "Дата рождения сотрудника") LocalDate birthdate,
             @RequestParam @Parameter(description = "Профессия сотрудника") String position,
             @RequestParam @Parameter(description = "Магазин сотрудника") String shop,
             @RequestParam @Parameter(description = "Пол сотрудника (\"m\" или \"f\")") String gender) {
        EmployeeEntity employee = employeeService.createEmployee(lastname, firstname,
                patronymic, birthdate, position, shop, gender);

        return ResponseEntity.created(URI.create("http::/localhost:8080/api/employees/"
                + employee.getId())).build();
    }

    @Operation(
            summary = "Удаляет сотрудника",
            description = "Позволяет удалить сотрудника под определенным id"
    )
    @DeleteMapping("{employee_id}")
    public ResponseEntity<Void> deleteEmployee
            (@PathVariable("employee_id") @Parameter(description = "Идентификатор сотрудника") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Изменяет сотрудника",
            description = "Позволяет изменяить полнсотью сотрудника"
    )
    @PutMapping
    public ResponseEntity<EmployeeDto> editEmployee(
            @RequestParam @Parameter(description = "Идентификатор сотрудника") Long id,
            @RequestParam @Parameter(description = "Фамилия сотрудника") String lastname,
            @RequestParam @Parameter(description = "Имя сотрудника") String firstname,
            @RequestParam @Parameter(description = "Отчество сотрудника") String patronymic,
            @RequestParam @Parameter(description = "Дата рождения сотрудника") LocalDate birthdate,
            @RequestParam @Parameter(description = "Профессия сотрудника") String position,
            @RequestParam @Parameter(description = "Магазин сотрудника") String shop,
            @RequestParam @Parameter(description = "Пол сотрудника (\"m\" или \"f\")") String gender) {

        EmployeeEntity employee = employeeService.editEmployee(id, lastname, firstname, patronymic,
                birthdate, position, shop, gender);

        return ResponseEntity.ok(factory.makeEmployeeDto(employee));
    }

    @Operation(
            summary = "Изменяет сотрудника",
            description = "Позволяет изменяить частично сотрудника"
    )
    @PatchMapping
    public ResponseEntity<EmployeeDto> updateEmployee
            (@RequestParam @Parameter(description = "Идентификатор сотрудника") Long id,
             @RequestParam(required = false) @Parameter(description = "Фамилия сотрудника") String lastname,
             @RequestParam(required = false) @Parameter(description = "Имя сотрудника") String firstname,
             @RequestParam(required = false) @Parameter(description = "Отчество сотрудника") String patronymic,
             @RequestParam(required = false) @Parameter(description = "Дата рождения сотрудника") LocalDate birthdate,
             @RequestParam(required = false) @Parameter(description = "Профессия сотрудника") String position,
             @RequestParam(required = false) @Parameter(description = "Магазин сотрудника") String shop,
             @RequestParam(required = false) @Parameter(description = "Пол сотрудника (\"m\" или \"f\")") String gender) {
        EmployeeEntity employee = employeeService.updateEmployee(id, lastname, firstname, patronymic,
                birthdate, position, shop, gender);

        return ResponseEntity.ok(factory.makeEmployeeDto(employee));
    }

}
