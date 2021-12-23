package com.example.kms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class JuniorChefHomePageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label EmployeeName;

    @FXML
    private Button ManageInventory;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private Button PlaceOrder;

    @FXML
    private Button RequestLeave;

    @FXML
    private Button Schedule;

    @FXML
    private Label empTypeLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Button homePage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label secondNameLabel;

    @FXML
    private Button signoutButton;

    @FXML
    private Label userNameLabel;
    private Stage primaryStage;

    @FXML
    void ManageInventoryButtonPressed(ActionEvent event)
    {
        try {
            changeScene("JuniorChefManageInventory.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void PlaceOrderButtonPressed(ActionEvent event)
    {
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

    }

    @FXML
    void signOutButtonPressed(ActionEvent event)
    {
        try {
            changeScene("LoginPage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void initialize() {

        Data data = Data.getDataInstance();

        EmployeeKMS emp = data.getMessage().getEmployeeObject();

        firstNameLabel.setText(emp.getFirstName());
        secondNameLabel.setText(emp.getLastName());
        userNameLabel.setText(emp.getUsername());
        empTypeLabel.setText(emp.getEmployeeType());

    }

    //Generic function to change scene
    public void changeScene(String fxml, ActionEvent event) throws IOException {

        //------Following code changes scene to Schedule-------
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        this.primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        //-----------------------------------------------------

    }

}
