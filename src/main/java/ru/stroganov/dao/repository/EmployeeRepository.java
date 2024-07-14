package ru.stroganov.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stroganov.dao.entity.EmployeeEntity;

import java.time.Instant;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByLastNameAndFirstNameAndPatronymicAndBirthDate(String lastName,
                                                                                String firstName,
                                                                                String patronymic,
                                                                                Instant birthDate);
}
