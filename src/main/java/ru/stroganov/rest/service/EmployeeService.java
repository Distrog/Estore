package ru.stroganov.rest.service;

import ru.stroganov.dao.entity.EmployeeEntity;

import java.time.LocalDate;
import java.util.Collection;

public interface EmployeeService {
    EmployeeEntity getEmployee(Long id);

    Collection<EmployeeEntity> getAllEmployees(Integer pageNumber, Integer pageSize);

    void deleteEmployee(Long id);

    EmployeeEntity createEmployee(String lastname, String firstname, String patronymic, LocalDate birthdate, String position, String shop, String gender);

    EmployeeEntity editEmployee(Long id, String lastname, String firstname, String patronymic, LocalDate birthdate, String position, String shop, String gender);

    EmployeeEntity updateEmployee(Long id, String lastname, String firstname, String patronymic, LocalDate birthdate, String position, String shop, String gender);
}
