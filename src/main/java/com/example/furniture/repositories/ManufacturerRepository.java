package com.example.furniture.repositories;

import com.example.furniture.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    List<Manufacturer> findManufacturerByNameOfManufacturerContaining(String nameOfManufacturer);

    Manufacturer findManufacturerByNameOfManufacturerAndAddressOfManufacturerAndPhoneNumberOfManufacturer(String nameOfManufacturer,
                                                                                                          String addressOfManufacturer,
                                                                                                          String phoneNumberOfManufacturer);
}