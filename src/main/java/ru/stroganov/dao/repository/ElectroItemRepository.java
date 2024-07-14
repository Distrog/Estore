package ru.stroganov.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stroganov.dao.entity.ElectroItemEntity;

public interface ElectroItemRepository extends JpaRepository<ElectroItemEntity,Long> {
}
