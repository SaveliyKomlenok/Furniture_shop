package com.example.furniture.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "sold_furniture")
@Data
@AllArgsConstructor
@NoArgsConstructor
@AssociationOverrides({
        @AssociationOverride(name = "id.sale",
                joinColumns = @JoinColumn(name = "id_sold_furniture")),
        @AssociationOverride(name = "id.furniture",
                joinColumns = @JoinColumn(name = "id_furniture_for_sale")) })
public class SoldFurniture {
    SoldFurnitureCompositionKey id = new SoldFurnitureCompositionKey();
    private int amount;
    @Column(name="id_sold_furniture")
    private Sales sale;
    @Column(name="id_furniture_for_sale")
    private Furniture furniture;
    @EmbeddedId
    public SoldFurnitureCompositionKey getId() {
        return id;
    }
    public void setId(SoldFurnitureCompositionKey id) {
        this.id = id;
    }
    @Column(name="amount_of_sold_furniture")
    public int getAmount() {
        return amount;
    }
    @Column(name="amount_of_sold_furniture")
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Transient
    public Sales getSale() {
        return sale;
    }

    public void setSale(Sales sale) {
        this.sale = sale;
    }
    @Transient
    public Furniture getFurniture() {
        return furniture;
    }
    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }


}
