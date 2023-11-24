package com.example.furniture.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "supplied_furniture")
@Data
@AllArgsConstructor
@NoArgsConstructor
@AssociationOverrides({
        @AssociationOverride(name = "id.supply",
                joinColumns = @JoinColumn(name = "id_supplied_furniture")),
        @AssociationOverride(name = "id.furniture",
                joinColumns = @JoinColumn(name = "id_furniture_for_supply")) })
public class SuppliedFurniture {
    SuppliedFurnitureCompositionKey id = new SuppliedFurnitureCompositionKey();

    private int amount;

    @Column(name = "id_supplied_furniture")
    private Supplies supply;

    @Column(name = "id_furniture_for_supply")
    private Furniture furniture;

    @EmbeddedId
    public SuppliedFurnitureCompositionKey getId(){
        return id;
    }
    public void setId(SuppliedFurnitureCompositionKey id){
        this.id = id;
    }

    @Column(name="amount_of_supplied_furniture")
    public int getAmount() {
        return amount;
    }
    @Column(name="amount_of_supplied_furniture")
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Transient
    public Supplies getSupply() {
        return supply;
    }

    public void setSupply(Supplies supply) {
        this.supply = supply;
    }
    @Transient
    public Furniture getFurniture() {
        return furniture;
    }
    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}
