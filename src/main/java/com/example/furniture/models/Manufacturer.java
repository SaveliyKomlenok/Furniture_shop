package com.example.furniture.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "manufacturer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_manufacturer")
    private Long id_manufacturer;

    @Column(name = "name_of_manufacturer")
    @Pattern(regexp = "^([A-ЯЁа-яё]+)(\\s|-)?([A-ЯЁа-яё]+)", message = "Поле должно содержать только буквы")
    @javax.validation.constraints.Size(min = 2, max = 200, message = "Длина названия должна быть между 2 и 200 символами")
    private String nameOfManufacturer;

    @Column(name = "address_of_manufacturer")
    @Pattern(regexp = "^(г\\.[А-ЯЁа-яё]+\\sул\\.[А-ЯЁа-яё]+\\sд\\.\\d+)", message = "Некорректный адрес, введите адрес в формате:г.Город ул.Улица д.Номер")
    private String addressOfManufacturer;

    @Column(name = "phone_number_of_manufacturer")
    @Pattern(regexp = "\\+375(17|25|29|33|44)\\d{7}", message = "Некорректный номер телефона, введите номер в формате: +375(код)1234567")
    private String phoneNumberOfManufacturer;
    @OneToMany(mappedBy = "manufacturer")
    private List<Furniture> manufacturerFurniture;
}
