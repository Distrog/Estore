package ru.stroganov.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.stroganov.dao.entity.ElectroTypeEntity;
import ru.stroganov.dao.repository.ElectroTypeRepository;
import ru.stroganov.exception.NotFoundElectroitemException;
import ru.stroganov.exception.NotFountElectroTypeException;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class ElectroTypeServiceImpl implements ElectroTypeService {
    private final ElectroTypeRepository electroTypeRepository;

    @Override
    public Collection<ElectroTypeEntity> getAllElectroTypes(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return electroTypeRepository.findAll(pageRequest).getContent();
    }

    @Override
    public ElectroTypeEntity getElectoType(Long id) {
        return electroTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundElectroitemException(String
                        .format("Не найдет тип электротовара №%d", id)));
    }

    @Override
    public ElectroTypeEntity getElectroTypeByName(String etypeName) {
        return electroTypeRepository.findByName(etypeName)
                .orElseThrow(() -> new NotFountElectroTypeException(String
                        .format("не найден тип электротоваров с названием %s", etypeName)));
    }

    @Override
    public ElectroTypeEntity createElectroType(String name) {
        ElectroTypeEntity electroType = ElectroTypeEntity.builder()
                .name(name)
                .build();

        return electroTypeRepository.save(electroType);
    }

    @Override
    public ElectroTypeEntity editElectroService(Long id, String name) {
       ElectroTypeEntity electroType = getElectoType(id);

       electroType.setName(name);

       return electroTypeRepository.save(electroType);
    }

    @Override
    public void deleteElectroType(Long id) {
        ElectroTypeEntity electroType = getElectoType(id);
        electroTypeRepository.delete(electroType);
    }
}
