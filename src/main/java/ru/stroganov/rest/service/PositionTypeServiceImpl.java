package ru.stroganov.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.stroganov.dao.entity.PositionTypeEntity;
import ru.stroganov.dao.repository.PositionTypeRepository;
import ru.stroganov.exception.NotFoundPositionTypeException;
import ru.stroganov.exception.NotFoundPositonException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PositionTypeServiceImpl implements PositionTypeService {
    private final PositionTypeRepository positionTypeRepository;

    @Override
    public List<PositionTypeEntity> getAllPositionTypes(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return positionTypeRepository.findAll(pageRequest).getContent();
    }

    @Override
    public PositionTypeEntity getPositionTypeByName(String positionTypeName) {
        return positionTypeRepository.findByName(positionTypeName).orElseThrow(() ->
                new NotFoundPositonException(String
                        .format("Должности с названием %s не существует", positionTypeName)));
    }

    @Override
    public PositionTypeEntity getPositionType(Long id) {
        return positionTypeRepository.findById(id).orElseThrow(() ->
                new NotFoundPositionTypeException(String
                        .format("не найдена должность №%d", id)));
    }

    @Override
    public PositionTypeEntity createPositiontype(String name) {
        PositionTypeEntity positionType = PositionTypeEntity.builder().name(name).build();
        return positionTypeRepository.save(positionType);
    }

    @Override
    public PositionTypeEntity editPositionType(Long id, String name) {
        PositionTypeEntity positionType = getPositionType(id);
        positionType.setName(name);
        return positionTypeRepository.save(positionType);
    }

    @Override
    public void deletePositiontype(Long id) {
        PositionTypeEntity positionType = getPositionType(id);
        positionTypeRepository.delete(positionType);
    }
}
