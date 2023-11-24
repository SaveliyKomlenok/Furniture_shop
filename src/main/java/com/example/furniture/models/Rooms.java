package com.example.furniture.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rooms")
    private Long id_rooms;

    @Column(name = "name_of_room")
    @Pattern(regexp = "^([A-ЯЁа-яё]+)(\\s|-)?([A-ЯЁа-яё]+)", message = "Поле должно содержать только буквы")
    @javax.validation.constraints.Size(min = 4, max = 200, message = "Длина названия должна быть между 2 и 200 символами")
    private String nameOfRoom;

    @OneToMany(mappedBy = "rooms")
    private List<Furniture> roomsFurniture;
}