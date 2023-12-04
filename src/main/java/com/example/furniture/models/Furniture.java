package com.example.furniture.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "furniture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_furniture")
    private Long id_furniture;

    @Pattern(regexp = "^([A-ЯЁа-яё]+)(\\s|-)?([A-ЯЁа-яё]+)",
            message = "Поле должно содержать только буквы")
    @javax.validation.constraints.Size(min = 3, max = 200,
            message = "Длина названия должна быть между 3 и 200 символами")
    @Column(name = "name_of_furniture")
    private String nameOfFurniture;

    @Column(name = "amount_of_furniture")
    private int amountOfFurniture;

    @Column(name = "price_of_furniture")
    private double priceOfFurniture;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Rooms rooms;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "id.furniture",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<SoldFurniture> soldFurnitureToFurniture;
    @OneToMany(mappedBy = "id.furniture",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
    private List<SuppliedFurniture> suppliedFurnitureToFurniture;
}