package com.example.furniture.repositories;

import com.example.furniture.models.Supplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliesRepository extends JpaRepository<Supplies, Long> {
    List<Supplies> findByDateOfSuppliesStartingWith(String date);
}
