package com.example.kms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class EmployeeHomePageController {

    @FXML
    private ComboBox<?> CashPayment;

    @FXML
    private Button Checkout;

    @FXML
    private Label Discount;

    @FXML
    private Label GST;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private Label Subtotal;

    @FXML
    private Label Total;

    @FXML
    private DatePicker dateSelector;

    @FXML
    private GridPane grid;

    @FXML
    private ListView<?> listview;

    @FXML
    private Button homePage;



    @FXML
    void ActionHandled(ActionEvent event) {

    }

    @FXML
    void HandleDateSelector(ActionEvent event) {

       // dateSelector.


        System.out.println(dateSelector.getValue());
    }


    @FXML
    void homePageButtonPressed(ActionEvent event) {

        System.out.println("Hello Home Page");

    }

    @FXML
    void signOutButtonPressed(ActionEvent event) {

        System.out.println("Sign Out Button Pressed");

    }

    @FXML
    void initialize() {

        /*DatePickerSkin datePickerSkin = new DatePickerSkin(dateSelector);
        Node popupContent = datePickerSkin.getPopupContent();

        //-----Code Below disables the date cells of date picker which are older than today--
        dateSelector.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
                if(date.compareTo(today) < 0) {
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });*/
        //-----------------------------------------------------------------------------------
    }

    @FXML
    void HoverHandled(MouseEvent event) {

    }

}
