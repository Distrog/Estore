package ru.stroganov.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String lastName;

    @Column(nullable = false,length = 100)
    private String firstName;

    @Column(nullable = false,length = 100)
    private String patronymic;

    @Column(nullable = false)
    private LocalDate birthDate;

    @ManyToOne
    private ShopEntity shop;

    @ManyToOne
    private PositionTypeEntity position;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "employee")
    private List<PurchaseEntity> purchases = new ArrayList<>();

    @Column(nullable = false)
    private Boolean gender;

    @JsonIgnore
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "ElectroEmployee",
            joinColumns = @JoinColumn(name = "employeeId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "electroTypeId", referencedColumnName = "id"))
    private List<ElectroTypeEntity> electroTypes =new ArrayList<>();
}
