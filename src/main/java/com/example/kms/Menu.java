package com.example.kms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Menu {


    Double Gst;
    Double Discount;
    ArrayList<Product> products;
    static Menu menu;

    public void setGst(Double gst) {
        Gst = gst;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    public Double getGst() {
        return Gst;
    }

    public Double getDiscount() {
        return Discount;
    }

    public Menu()
    {
        Discount=0.0;
        Gst=5.0;

    }

    public static Menu GetInstance()
    {
        if(menu==null)
        {
            menu=new Menu();
        }
        return menu;
    }



    ObservableList<String> PaymentMethod= FXCollections.observableArrayList (
            "Debit Card ",  "Pay Cash");

    public void setPaymentMethod(ObservableList<String> paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public ObservableList<String> getPaymentMethod() {
        return PaymentMethod;
    }

    public void setProducts(ArrayList<Product> products) {

        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
