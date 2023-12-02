package com.example.furniture.repositories;

import com.example.furniture.models.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findByDateOfSalesStartingWith(String date);
}
