package com.example.furniture.repositories;

import com.example.furniture.models.SuppliedFurniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliedFurnitureRepository extends JpaRepository<SuppliedFurniture, Long> {

}
