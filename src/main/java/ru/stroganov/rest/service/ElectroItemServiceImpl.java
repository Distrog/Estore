package ru.stroganov.rest.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.stroganov.dao.entity.ElectroItemEntity;
import ru.stroganov.dao.repository.ElectroItemRepository;
import ru.stroganov.exception.NotFoundElectroitemException;

import java.util.Collection;

@AllArgsConstructor
@Service
public class ElectroItemServiceImpl implements ElectroItemService {
    private final ElectroItemRepository electroItemRepository;
    private final ElectroTypeService electroTypeService;

    @Override
    public Collection<ElectroItemEntity> getAllElectroItems(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber-1,pageSize);
        return electroItemRepository.findAll(pageRequest).getContent();
    }

    @Override
    public ElectroItemEntity getElectroItem(Long id) {
        return electroItemRepository.findById(id).orElseThrow(() ->
                new NotFoundElectroitemException(String.format("не найден электротовар №%d", id)));
    }

    @Override
    public ElectroItemEntity createElectroItem(String name, String etype, Long price,
                                               Integer count, Boolean archive, String description) {
        ElectroItemEntity electroItem = ElectroItemEntity.builder()
                .name(name)
                .etype(electroTypeService.getElectroTypeByName(etype))
                .price(price)
                .count(count)
                .archive(archive)
                .description(description)
                .build();

        return electroItemRepository.save(electroItem);
    }

    @Override
    public ElectroItemEntity editElectroItem(Long id, String name, String etype,
                                             Long price, Integer count, Boolean archive,
                                             String description) {

        ElectroItemEntity electroItem = getElectroItem(id);
        electroItem.setName(name);
        electroItem.setEtype(electroTypeService.getElectroTypeByName(etype));
        electroItem.setPrice(price);
        electroItem.setCount(count);
        electroItem.setArchive(archive);
        electroItem.setDescription(description);

        return electroItemRepository.save(electroItem);
    }

    @Override
    public ElectroItemEntity updateElectroitem(Long id, String name, String etype, Long price,
                                               Integer count, Boolean archive, String description) {
        ElectroItemEntity electroItem = getElectroItem(id);
        if (name != null) {
            electroItem.setName(name);
        }
        if (etype != null) {
            electroItem.setEtype(electroTypeService.getElectroTypeByName(etype));
        }
        if (price != null) {
            electroItem.setPrice(price);
        }
        if (count != null) {
            electroItem.setCount(count);
        }
        if (archive != null) {
            electroItem.setArchive(archive);
        }
        if (description!= null) {
            electroItem.setDescription(description);
        }

        return electroItemRepository.save(electroItem);
    }

    @Override
    public void deleteElectroItem(Long id) {
        ElectroItemEntity electroItem = getElectroItem(id);
        electroItemRepository.delete(electroItem);
    }


}
