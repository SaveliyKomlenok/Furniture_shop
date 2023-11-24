package com.example.furniture.util;

import com.example.furniture.models.Furniture;
import com.example.furniture.repositories.FurnitureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FurnitureValidator implements Validator {
    private final FurnitureRepository furnitureRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return Furniture.class.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Furniture furniture = (Furniture) target;

        Furniture duplicateFurniture = furnitureRepository.findFurnitureByNameOfFurnitureAndPriceOfFurnitureAndSizeAndRoomsAndManufacturer(
                furniture.getNameOfFurniture(),
                furniture.getPriceOfFurniture(),
                furniture.getSize(),
                furniture.getRooms(),
                furniture.getManufacturer());

        if(duplicateFurniture != null && !Objects.equals(duplicateFurniture.getId_furniture(), furniture.getId_furniture())){
            errors.rejectValue("nameOfFurniture", "", "Данная мебель уже существует");
        }
    }
}
