package com.example.furniture.services;

import com.example.furniture.models.Manufacturer;
import com.example.furniture.models.Rooms;
import com.example.furniture.repositories.RoomsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomsService {
    private final RoomsRepository roomsRepository;

    public List<Rooms> listRooms(String nameOfRoom){
        if(nameOfRoom != null) return roomsRepository.findRoomsByNameOfRoomStartingWith(nameOfRoom);
        return roomsRepository.findAll();
    }

    public List<Rooms> sortRoomsByName(List<Rooms> roomsList, boolean isSorted){
        if(isSorted){
            roomsList.sort(Comparator.comparing(Rooms::getNameOfRoom));
        }
        else{
            roomsList.sort(Comparator.comparing(Rooms::getNameOfRoom).reversed());
        }
        return roomsList;
    }

    public void saveRooms(Rooms rooms){
        roomsRepository.save(rooms);
    }

    public void deleteRooms(Long id_rooms){
        roomsRepository.deleteById(id_rooms);
    }

    public void editRoom(Long id_room, Rooms room){
        Rooms newRoom = getRoomsById(id_room);
        newRoom.setNameOfRoom(room.getNameOfRoom());
        roomsRepository.save(room);
    }

    public Rooms getRoomsById(Long id){
        return roomsRepository.findById(id).orElse(null);
    }
}
