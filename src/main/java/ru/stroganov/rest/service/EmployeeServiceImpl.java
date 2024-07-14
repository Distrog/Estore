package ru.stroganov.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.stroganov.dao.entity.EmployeeEntity;
import ru.stroganov.dao.repository.EmployeeRepository;
import ru.stroganov.exception.GendetFormatException;
import ru.stroganov.exception.NotFoundEmployeeException;

import java.time.LocalDate;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionTypeService positionTypeService;
    private final ShopService shopService;

    @Override
    public EmployeeEntity getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundEmployeeException(String
                        .format("Не найден работник с Id #%d", id)));
    }

    @Override
    public Collection<EmployeeEntity> getAllEmployees(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return employeeRepository.findAll(pageRequest).getContent();
    }

    @Override
    public void deleteEmployee(Long id) {
        EmployeeEntity employee = getEmployee(id);
        employeeRepository.delete(employee);
    }

    @Override
    public EmployeeEntity createEmployee(String lastname, String firstname,
                                         String patronymic, LocalDate birthdate,
                                         String position, String shop, String gender) {


        EmployeeEntity employee = EmployeeEntity.builder()
                .lastName(lastname)
                .firstName(firstname)
                .patronymic(patronymic)
                .birthDate(birthdate)
                .shop(shopService.getShopByName(shop))
                .position(positionTypeService.getPositionTypeByName(position))
                .gender(convertStringToGender(gender))
                .build();

        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity editEmployee(Long id, String lastname, String firstname, String patronymic,
                                       LocalDate birthdate, String position, String shop, String gender) {
        EmployeeEntity employee = getEmployee(id);

        employee.setLastName(lastname);
        employee.setFirstName(firstname);
        employee.setPatronymic(patronymic);
        employee.setBirthDate(birthdate);
        employee.setPosition(positionTypeService.getPositionTypeByName(position));
        employee.setShop(shopService.getShopByName(shop));
        employee.setGender(convertStringToGender(gender));

        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity updateEmployee(Long id, String lastname, String firstname, String patronymic,
                                         LocalDate birthdate, String position, String shop, String gender) {
        EmployeeEntity employee = getEmployee(id);

        if (lastname != null) {
            employee.setLastName(lastname);
        }
        if (firstname != null) {
            employee.setFirstName(firstname);
        }
        if (patronymic != null) {
            employee.setPatronymic(patronymic);
        }
        if (birthdate != null) {
            employee.setBirthDate(birthdate);
        }
        if (position != null) {
            employee.setPosition(positionTypeService.getPositionTypeByName(position));
        }
        if (shop != null) {
            employee.setShop(shopService.getShopByName(shop));
        }
        if (gender != null) {
            employee.setGender(convertStringToGender(gender));

        }

        return employeeRepository.save(employee);
    }

    private Boolean convertStringToGender(String genderString) {
        boolean isGender;

        if (genderString.equalsIgnoreCase("m")) {
            isGender = true;
        } else if (genderString.equalsIgnoreCase("f")) {
            isGender = false;
        } else {
            throw new GendetFormatException("не правильно введен пол (\"m\" - мужской пол, " +
                    "\"f\" - женский пол)");
        }

        return isGender;
    }

}
