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
@Table(name = "electro_item")
public class ElectroItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 150)
    private String name;

    @ManyToOne
    private ElectroTypeEntity etype;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Boolean archive;

    @Column(nullable = false)
    private String description;

    @JsonIgnore
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "ElectroShop",
            joinColumns = @JoinColumn(name = "electroItemId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shopId", referencedColumnName = "id"))
    private List<ShopEntity> shops = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "electro")
    private List<PurchaseEntity> purchases;
}
