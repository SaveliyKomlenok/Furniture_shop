package com.example.furniture.util;

import com.example.furniture.models.Manufacturer;
import com.example.furniture.repositories.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ManufacturerValidator implements Validator {
    private final ManufacturerRepository manufacturerRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return Manufacturer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Manufacturer manufacturer = (Manufacturer) target;

        Manufacturer duplicateManufacturer = manufacturerRepository.findManufacturerByNameOfManufacturerAndAddressOfManufacturerAndPhoneNumberOfManufacturer(
                manufacturer.getNameOfManufacturer(),
                manufacturer.getAddressOfManufacturer(),
                manufacturer.getPhoneNumberOfManufacturer());

        if(duplicateManufacturer != null && !Objects.equals(duplicateManufacturer.getId_manufacturer(), manufacturer.getId_manufacturer())){
            errors.rejectValue("nameOfManufacturer", "", "Данный изготовитель уже существует");
        }
    }
}
