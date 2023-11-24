package com.example.furniture.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "supplies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supplies")
    private Long id_supplies;
    @Column(name = "date_of_supplies")
    private String dateOfSupplies;
    @ManyToOne
    @JoinColumn(name = "id_staff_for_supplies")
    private Staff staff;
    @PrePersist
    private void init(){
        dateOfSupplies = String.valueOf(LocalDate.now());
    }
    @OneToMany(mappedBy = "id.supply",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<SuppliedFurniture> suppliedFurnitureToSupplies;
}
