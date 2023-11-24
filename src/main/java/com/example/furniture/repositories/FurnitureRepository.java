package com.example.furniture.repositories;

import com.example.furniture.models.Furniture;
import com.example.furniture.models.Manufacturer;
import com.example.furniture.models.Rooms;
import com.example.furniture.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
    List<Furniture> findFurnitureByNameOfFurnitureContaining(String nameOfFurniture);
    Furniture findFurnitureByNameOfFurnitureAndPriceOfFurnitureAndSizeAndRoomsAndManufacturer(String nameOfFurniture,
                                                                                               double priceOfFurniture,
                                                                                               Size size, Rooms rooms, Manufacturer manufacturer);
}