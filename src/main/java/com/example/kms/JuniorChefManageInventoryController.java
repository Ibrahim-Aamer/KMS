package com.example.kms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JuniorChefManageInventoryController {

    @FXML
    private Label EmployeeName;

    @FXML
    private Button ManageInventory;

    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private Button PlaceOrder;

    @FXML
    private TextField ProductName;

    @FXML
    private Label PromptMessage;

    @FXML
    private TextField Quantity;

    @FXML
    private Button RequestLeave;

    @FXML
    private Button Schedule;

    @FXML
    private Button SubmitIngredient;

    @FXML
    private Button homePage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;
    private Stage primaryStage;


    @FXML
    void SubmitIngredient(ActionEvent event)
    {
        if (event.getSource() == SubmitIngredient)
        {
            //System.out.println(ProductName.getText()+" "+ Integer.parseInt(Quantity.getText()));

            try {
                if (!ProductName.getText().isEmpty()) {
                    JuniorChef chef = JuniorChef.getInstance();
                    chef.AddIngredient(ProductName.getText(), Integer.parseInt(Quantity.getText()));
                    PromptMessage.setText("Ingredient has been Added");
                    Clear();
                } else {
                    PromptMessage.setText("Ingredient Not Added (Name Can't be Empty)");
                }
            }
            catch(final NumberFormatException e)
            {
                PromptMessage.setText("Ingredient Not Added (Add a Correct Number)");
                System.err.println(e.getMessage());//if parse int not correct
            }
        }
    }

    public boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void Clear()
    {
        ProductName.setText("");
        Quantity.setText("");
    }

    @FXML
    void ManageInventoryButtonPressed(ActionEvent event)
    {

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
    void signOutButtonPressed(ActionEvent event)
    {
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

}
