package ru.stroganov.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroganov.dao.entity.ElectroItemEntity;

import java.util.Collection;

public interface ElectroItemService {

    Collection<ElectroItemEntity> getAllElectroItems(Integer pageNumber, Integer pageSize);

    ElectroItemEntity getElectroItem(Long id);

    ElectroItemEntity createElectroItem(String name, String etype, Long price,
                                        Integer count, Boolean archive, String description);

    ElectroItemEntity editElectroItem(Long id, String name, String etype, Long price,
                                      Integer count, Boolean archive, String description);

    ElectroItemEntity updateElectroitem(Long id, String name, String etype, Long price,
                                        Integer count, Boolean archive, String description);

    void deleteElectroItem(Long id);
}
