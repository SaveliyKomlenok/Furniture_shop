package com.example.furniture.models;

import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@RequiredArgsConstructor
public class SoldFurnitureCompositionKey implements Serializable {
    private Sales sale;
    private Furniture furniture;
    public SoldFurnitureCompositionKey(Sales sale, Furniture furniture) {
        this.sale = sale;
        this.furniture = furniture;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Sales getSale() {
        return sale;
    }
    public void setSale(Sales sale) {
        this.sale = sale;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    public Furniture getFurniture() {
        return furniture;
    }
    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}
