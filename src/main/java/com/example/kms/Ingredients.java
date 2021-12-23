package com.example.kms;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="kms_ingredients")
public class Ingredients implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;

    private String Name;

    private int Quantity;

    public Ingredients(){};

    public Ingredients(int q, String n)
    {
        Quantity=q;
        Name=n;
    }

    public int getId()
    {
        return id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getName() {
        return Name;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setName(String name) {
        Name = name;
    }
}
