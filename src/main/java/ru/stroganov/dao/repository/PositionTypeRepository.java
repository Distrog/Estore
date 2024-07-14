package ru.stroganov.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stroganov.dao.entity.PositionTypeEntity;

import java.util.Optional;

public interface PositionTypeRepository extends JpaRepository<PositionTypeEntity,Long> {
    Optional<PositionTypeEntity> findByName(String name);
}
