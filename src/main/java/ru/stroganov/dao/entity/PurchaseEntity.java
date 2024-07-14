package ru.stroganov.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "purchase")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ElectroItemEntity electro;

    @ManyToOne
    private EmployeeEntity employee;

    @Column(nullable = false)
    private LocalDate purchaseDate;

    @ManyToOne
    private PurchaseTypeEntity type;

    @ManyToOne
    private ShopEntity shop;
}
