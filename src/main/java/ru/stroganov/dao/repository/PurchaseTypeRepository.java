package ru.stroganov.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stroganov.dao.entity.PurchaseTypeEntity;

import java.util.Optional;

public interface PurchaseTypeRepository extends JpaRepository<PurchaseTypeEntity,Long> {
    Optional<PurchaseTypeEntity> findByName(String name);
}
