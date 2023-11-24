package com.example.furniture.services;

import com.example.furniture.models.Furniture;
import com.example.furniture.repositories.FurnitureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class FurnitureService {
    private final FurnitureRepository furnitureRepository;

    public List<Furniture> listFurniture(String nameOfFurniture){
        if(nameOfFurniture != null) return furnitureRepository.findFurnitureByNameOfFurnitureContaining(nameOfFurniture);
        return furnitureRepository.findAll();
    }

    public void saveFurniture(Furniture furniture){
        furniture.setAmountOfFurniture(0);
        furnitureRepository.save(furniture);
    }

    public void deleteFurniture(Long id_furniture){
        furnitureRepository.deleteById(id_furniture);
    }

    public void editFurniture(Long id_furniture, Furniture furniture){
        Furniture newFurniture = getFurnitureById(id_furniture);
        newFurniture.setNameOfFurniture(furniture.getNameOfFurniture());
        newFurniture.setPriceOfFurniture(furniture.getPriceOfFurniture());
        newFurniture.setAmountOfFurniture(furniture.getAmountOfFurniture());
        newFurniture.setSize(furniture.getSize());
        newFurniture.setRooms(furniture.getRooms());
        newFurniture.setManufacturer(furniture.getManufacturer());
        furnitureRepository.save(furniture);
    }

    public Furniture getFurnitureById(Long id){
        return furnitureRepository.findById(id).orElse(null);
    }
}
