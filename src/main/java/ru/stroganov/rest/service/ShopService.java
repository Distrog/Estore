package ru.stroganov.rest.service;

import ru.stroganov.dao.entity.ShopEntity;

import java.util.Collection;

public interface ShopService {
    ShopEntity getShopByName(String name);

    Collection<ShopEntity> getAllShops(Integer pageNumber, Integer pageSize);

    ShopEntity getShop(Long id);

    ShopEntity createShop(String name, String address);

    ShopEntity editIShop(Long id, String name, String address);

    void deleteShop(Long id);

    ShopEntity updateShop(Long id, String name, String address);
}
