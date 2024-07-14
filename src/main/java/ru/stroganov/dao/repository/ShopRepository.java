package ru.stroganov.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stroganov.dao.entity.ShopEntity;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<ShopEntity,Long> {
    Optional<ShopEntity> findByName(String name);
}
