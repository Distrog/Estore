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
@Table(name = "shop")
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 150)
    private String name;

    @Column(unique = true)
    private String address;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "shop")
    private List<PurchaseEntity> purchases = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ElectroShop",
            joinColumns = @JoinColumn(name = "shopId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "electroItemId", referencedColumnName = "id"))
    private List<ElectroItemEntity> electroItems = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "shop")
    private List<EmployeeEntity> employees=new ArrayList<>();
}
