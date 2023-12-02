package com.example.furniture.services;

import com.example.furniture.models.*;
import com.example.furniture.repositories.SuppliedFurnitureRepository;
import com.example.furniture.repositories.SuppliesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SuppliesService {
    private final SuppliesRepository suppliesRepository;
    private final FurnitureService furnitureService;

    private final SuppliedFurnitureRepository suppliedFurnitureRepository;

    public List<Supplies> listSupplies(String dateOfSupplies){
        if(dateOfSupplies!=null) return suppliesRepository.findByDateOfSuppliesStartingWith(dateOfSupplies);
        return suppliesRepository.findAll();
    }

    public Supplies addNewFurnitureInSupply(Supplies supply, Furniture furniture, int amountOfFurniture){
        SuppliedFurniture suppliedFurniture = new SuppliedFurniture();

        suppliedFurniture.setSupply(supply);
        suppliedFurniture.setFurniture(furniture);
        suppliedFurniture.setAmount(amountOfFurniture);
        suppliedFurniture.setId(new SuppliedFurnitureCompositionKey(supply, furniture));

        supply.getSuppliedFurnitureToSupplies().add(suppliedFurniture);
        return supply;
    }

    public void saveSupplies(Supplies supplies){
        suppliesRepository.save(supplies);
        suppliedFurnitureRepository.saveAll(supplies.getSuppliedFurnitureToSupplies());
    }

    public Supplies deleteNewSuppliedFurniture(int indexNewSuppliedFurniture, Supplies supply){
        supply.getSuppliedFurnitureToSupplies().remove(supply.getSuppliedFurnitureToSupplies().get(indexNewSuppliedFurniture));
        return supply;
    }

    public void deleteSupplies(Long id_supplies){
        suppliesRepository.deleteById(id_supplies);
    }

    public Supplies getSuppliesById(Long id){
        return suppliesRepository.findById(id).orElse(null);
    }

    public Supplies addFurnitureToSupply(Supplies supply, Long id_furniture, int amountOfFurniture) {
        Furniture furniture = furnitureService.getFurnitureById(id_furniture);
        Optional<SuppliedFurniture> suppliedFurniture = supply.getSuppliedFurnitureToSupplies()
                .stream()
                .filter(o -> o.getFurniture().getId_furniture().equals(furniture.getId_furniture())).findFirst();
        if (suppliedFurniture.isPresent()) {
            Supplies finalSupply = supply;
            suppliedFurniture.map(p -> {
                p.setAmount(p.getAmount() + amountOfFurniture);
                return finalSupply;
            });
        } else {
            supply = addNewFurnitureInSupply(supply, furnitureService.getFurnitureById(id_furniture), amountOfFurniture);
        }
        return supply;
    }
}
