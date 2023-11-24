package com.example.furniture.util;

import com.example.furniture.models.Rooms;
import com.example.furniture.repositories.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RoomsValidator implements Validator {
    private final RoomsRepository roomsRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Rooms.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Rooms room = (Rooms) target;

        Rooms duplicateRoom = roomsRepository.findRoomsByNameOfRoom(room.getNameOfRoom());

        if(duplicateRoom != null && !Objects.equals(duplicateRoom.getId_rooms(), room.getId_rooms())){
            errors.rejectValue("nameOfRoom", "", "Данная комната уже существует");
        }

        if(room.getId_rooms() != null){
            if (!roomsRepository.existsById(room.getId_rooms())) {
                errors.rejectValue("id_rooms", "", "Введите существующий номер комнаты");
            }
        }
    }
}
