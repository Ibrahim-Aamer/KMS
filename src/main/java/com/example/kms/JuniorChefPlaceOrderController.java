package com.example.kms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JuniorChefPlaceOrderController implements Initializable {

    @FXML
    private TextArea Description;

    @FXML
    private Label EmployeeName;

    @FXML
    private Button ManageInventory;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private Button PlaceOrder;

    @FXML
    private Label PromptMessage;

    @FXML
    private Button RequestLeave;

    @FXML
    private Button Schedule;

    @FXML
    private Button SubmitOrder;

    @FXML
    private TextField SupplierName;

    @FXML
    private TextField SupplierNumber;

    @FXML
    private Button homePage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;
    private Stage primaryStage;

    @FXML
    void ManageInventoryButtonPressed(ActionEvent event) {

        //Changing scene
        try {
            changeScene("JuniorChefManageInventory.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }


    @FXML
    void SubmitOrder(ActionEvent event) {
        if(event.getSource()==SubmitOrder)
        {
            if(!SupplierName.getText().equals("") && !SupplierNumber.getText().equals("") && !Description.getText().equals("")) {

                JuniorChef chef = JuniorChef.getInstance();
                chef.AddSupplierOrder(SupplierName.getText(), SupplierNumber.getText(), Description.getText());
                PromptMessage.setText("Order has been put forth");
                Clear();
            }
            else
            {
                PromptMessage.setText("You have left the fields empty");
            }
        }

    }

    public void Clear()
    {
        SupplierName.setText("");
        SupplierNumber.setText("");
        Description.setText("");
    }

    @FXML
    void PlaceOrderButtonPressed(ActionEvent event)
    {

        //Changing scene
        try {
            changeScene("JuniorChefPlaceOrder.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }


    }

    @FXML
    void RequestLeaveButtonPressed(ActionEvent event)
    {

        //Changing scene
        try {
            changeScene("JuniorChefRequestLeave.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void goToSchedule(ActionEvent event)
    {

        //Changing scene
        try {
            changeScene("JuniorChefSchedule.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }


    }

    @FXML
    void homePageButtonPressed(ActionEvent event)
    {

        //Changing scene
        try {
            changeScene("JuniorChefHomePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void signOutButtonPressed(ActionEvent event) {

        //Changing scene
        try {
            changeScene("LoginPage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }
    public void changeScene(String fxml, ActionEvent event) throws IOException {

        //------Following code changes scene to Schedule-------
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        //-----------------------------------------------------

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JuniorChef chef=JuniorChef.getInstance();
        SupplierName.setText("");
        SupplierNumber.setText("");
        Description.setText("");

    }


    public void taskDatePickerPressed(ActionEvent actionEvent) {
    }
}

