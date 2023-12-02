package com.example.furniture.services;

import com.example.furniture.models.Manufacturer;
import com.example.furniture.repositories.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> listManufacturer(String nameOfManufacturer){
        if(nameOfManufacturer != null) return manufacturerRepository.findManufacturerByNameOfManufacturerStartingWith(nameOfManufacturer);
        return manufacturerRepository.findAll();
    }

    public void saveManufacturer(Manufacturer manufacturer){
        manufacturerRepository.save(manufacturer);
    }

    public void deleteManufacturer(Long id_manufacturer){
        manufacturerRepository.deleteById(id_manufacturer);
    }

    public void editManufacturer(Long id_manufacturer, Manufacturer manufacturer) {
        Manufacturer newManufacturer = getManufacturerById(id_manufacturer);
        newManufacturer.setNameOfManufacturer(manufacturer.getNameOfManufacturer());
        newManufacturer.setAddressOfManufacturer(manufacturer.getAddressOfManufacturer());
        newManufacturer.setPhoneNumberOfManufacturer(manufacturer.getAddressOfManufacturer());
        manufacturerRepository.save(manufacturer);
    }

    public Manufacturer getManufacturerById(Long id){
        return manufacturerRepository.findById(id).orElse(null);
    }
}
