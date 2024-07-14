package ru.stroganov.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stroganov.dao.entity.ElectroTypeEntity;

import java.util.Optional;

public interface ElectroTypeRepository extends JpaRepository<ElectroTypeEntity,Long> {

    Optional<ElectroTypeEntity> findByName(String name);
}
