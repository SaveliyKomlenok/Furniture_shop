package com.example.furniture.repositories;

import com.example.furniture.models.SoldFurniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldFurnitureRepository extends JpaRepository<SoldFurniture, Long> {

}