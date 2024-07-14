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
@Table(name = "position_type")
public class PositionTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true,nullable = false,length = 150)
    private String name;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "position")
    private List<EmployeeEntity> employees = new ArrayList<>();
}
