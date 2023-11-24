package com.example.furniture.models;

import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@RequiredArgsConstructor
public class SuppliedFurnitureCompositionKey implements Serializable {
    private Supplies supply;
    private Furniture furniture;

    public SuppliedFurnitureCompositionKey(Supplies supply, Furniture furniture) {
        this.supply = supply;
        this.furniture = furniture;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Supplies getSupply() {
        return supply;
    }
    public void setSupply(Supplies supply) {
        this.supply = supply;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Furniture getFurniture() {
        return furniture;
    }
    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}
