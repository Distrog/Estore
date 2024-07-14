package ru.stroganov.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "electro_type")
public class ElectroTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 150)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "etype")
    private List<ElectroItemEntity> electroItems = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "type")
    private List<PurchaseEntity> purchases = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "ElectroEmployee",
            joinColumns = @JoinColumn(name = "electroTypeId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employeeId", referencedColumnName = "id"))
    private List<EmployeeEntity> employees = new ArrayList<>();
}
