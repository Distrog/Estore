package ru.stroganov.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.stroganov.dao.entity.ShopEntity;
import ru.stroganov.dao.repository.ShopRepository;
import ru.stroganov.exception.NotFoundShopException;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    @Override
    public ShopEntity getShopByName(String name) {
        return shopRepository.findByName(name).orElseThrow(() ->
                new NotFoundShopException(String.format("Магазин с названием %s не существует", name)));
    }

    @Override
    public Collection<ShopEntity> getAllShops(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return shopRepository.findAll(pageRequest).getContent();
    }

    @Override
    public ShopEntity getShop(Long id) {
        return shopRepository.findById(id).orElseThrow(() ->
                new NotFoundShopException(String
                        .format("не найден магазин №%d", id)));
    }

    @Override
    public ShopEntity createShop(String name, String address) {
        ShopEntity shop = ShopEntity.builder().name(name).address(address).build();
        return shopRepository.save(shop);
    }

    @Override
    public ShopEntity editIShop(Long id, String name, String address) {
        ShopEntity shop = getShop(id);

        shop.setName(name);
        shop.setAddress(address);

        return shopRepository.save(shop);
    }

    @Override
    public void deleteShop(Long id) {
        ShopEntity shop = getShop(id);
        shopRepository.delete(shop);
    }

    @Override
    public ShopEntity updateShop(Long id, String name, String address) {
        ShopEntity shop = getShop(id);

        if (name != null) {
            shop.setName(name);
        }
        if (address != null) {
            shop.setAddress(address);
        }

        return shopRepository.save(shop);
    }
}
