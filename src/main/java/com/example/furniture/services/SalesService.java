package com.example.furniture.services;

import com.example.furniture.models.*;
import com.example.furniture.repositories.SoldFurnitureRepository;
import com.example.furniture.repositories.SalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;
    private final SoldFurnitureRepository soldFurnitureRepository;

    private final FurnitureService furnitureService;

    public List<Sales> listSales(String dateOfSales) {
        if (dateOfSales != null) return salesRepository.findByDateOfSalesContaining(dateOfSales);
        return salesRepository.findAll();
    }

    public Sales addNewFurnitureInSale(Sales sale, Furniture furniture, int amountOfFurniture) {
        SoldFurniture soldFurniture = new SoldFurniture();

        soldFurniture.setSale(sale);
        soldFurniture.setFurniture(furniture);
        soldFurniture.setAmount(amountOfFurniture);
        soldFurniture.setId(new SoldFurnitureCompositionKey(sale, furniture));

        sale.getSoldFurnitureToSales().add(soldFurniture);
        return sale;
    }

    public void saveSales(Sales sale) {
        salesRepository.save(sale);
        soldFurnitureRepository.saveAll(sale.getSoldFurnitureToSales());
    }

    public Sales deleteNewSoldFurniture(int indexNewSoldFurniture, Sales sale) {
        sale.getSoldFurnitureToSales().remove(indexNewSoldFurniture);
        return sale;
    }

    public void deleteSales(Long id_sales) {
        salesRepository.deleteById(id_sales);
    }

    public Sales getSalesById(Long id) {
        return salesRepository.findById(id).orElse(null);
    }

    public Sales addFurnitureToSale(Sales sale, Long id_furniture, int amountOfFurniture) {
        Furniture furniture = furnitureService.getFurnitureById(id_furniture);
        Optional<SoldFurniture> soldFurniture = sale.getSoldFurnitureToSales()
                .stream()
                .filter(o -> o.getFurniture().getId_furniture().equals(furniture.getId_furniture())).findFirst();
        if (soldFurniture.isPresent()) {
            Sales finalSupply = sale;
            soldFurniture.map(p -> {
                p.setAmount(p.getAmount() + amountOfFurniture);
                return finalSupply;
            });
        } else {
            sale = addNewFurnitureInSale(sale, furnitureService.getFurnitureById(id_furniture), amountOfFurniture);
        }
        return sale;
    }

    public int getAvailableAmount(Long id_furniture, Sales sale) {
        int soldAmount = sale.getSoldFurnitureToSales()
                .stream()
                .filter(o -> o.getFurniture().getId_furniture().equals(id_furniture)).findFirst().map(SoldFurniture::getAmount).orElse(0);
        return furnitureService.getFurnitureById(id_furniture).getAmountOfFurniture() - soldAmount;
    }
}
