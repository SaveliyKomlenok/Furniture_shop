package com.example.furniture.repositories;

import com.example.furniture.models.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    List<Rooms> findRoomsByNameOfRoomContaining(String nameOfRoom);

    Rooms findRoomsByNameOfRoom(String nameOfRoom);
}

