package com.example.furniture.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sales")
    private Long id_sales;
    @Column(name = "date_of_sales")
    private String dateOfSales;
    @ManyToOne
    @JoinColumn(name = "id_staff_for_sales")
    private Staff staff;
    @PrePersist
    private void init(){
        dateOfSales = String.valueOf(LocalDate.now());
    }

    @OneToMany(mappedBy = "id.sale",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<SoldFurniture> soldFurnitureToSales;
}
