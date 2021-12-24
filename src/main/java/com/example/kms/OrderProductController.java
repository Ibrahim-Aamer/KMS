package com.example.kms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderProductController implements Initializable {


    @FXML
    private Label productName;

    @FXML
    private Label productQuantity;

    public String getProductName() {
        return productName.getText();
    }

    public int getQuantity() {
        return Integer.parseInt(productQuantity.getText());
    }

    public void setProductName(String product) {
        productName.setText(product);
    }

    public void setQuantity(int quantity) {
        productQuantity.setText(Integer.toString(quantity));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
