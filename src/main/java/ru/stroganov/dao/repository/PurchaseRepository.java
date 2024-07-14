package ru.stroganov.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.stroganov.dao.entity.PurchaseEntity;
public interface PurchaseRepository extends JpaRepository<PurchaseEntity,Long> {
//    Лучшие сотрудники по количеству проданных товаров
//    select employee.last_name, employee.first_name, employee.patronymic, count(purchase.id) from purchase
//    inner join employee on purchase.employee_id=employee.id
//    inner join position_type on position_type.id=employee.position_id
//    where position_type.name = 'продавец' and age(purchase.purchase_date)<= '1 year'
//    group by employee.id

//    Лучшие работники по сумме проданных товаров
//    select employee.last_name, employee.first_name, employee.patronymic,sum(electro_item.price) from purchase
//    inner join employee on purchase.employee_id=employee.id
//    inner join position_type on position_type.id=employee.position_id
//    inner join electro_item on electro_item.id = purchase.electro_id
//    where position_type.name = 'продавец' and age(purchase.purchase_date)<= '1 year'
//    group by employee.id

//    лучший младший консультант продавший больше всего часов
//    select employee.last_name, employee.first_name, employee.patronymic, count(purchase.id) from employee
//    inner join purchase on purchase.employee_id=employee.id
//    inner join electro_item on electro_item.id=purchase.electro_id
//    inner join electro_type on electro_item.etype_id=electro_type.id
//    inner join position_type on position_type.id = employee.position_id
//    where electro_type.name = 'Умные часы' and position_type.name = 'младший продавец консультант'
//    group by employee.id

//    сумма оплаченная наличными
//    select sum(electro_item.price) from purchase
//    inner join electro_item on electro_item.id=purchase.electro_id
//    inner join purchase_type on purchase_type.id=purchase.type_id
//    where purchase_type.name = 'наличными'
}
