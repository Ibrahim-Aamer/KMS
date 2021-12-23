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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SousChefHomePageController implements Initializable {

    public Label firstNameLabel;
    public Label secondNameLabel;
    public Label userNameLabel;
    public Label empTypeLabel;
    @FXML
    private ScrollPane MenuScroll;

    @FXML
    private Label PromptMessage;

    @FXML
    private Button goToRequestLeave;

    @FXML
    private Button goToMealPrep;

    @FXML
    private Button goToSchedule;

    @FXML
    private Button homePage;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;
    private Stage primaryStage;

    @FXML
    void homePageButtonPressed(ActionEvent event) {

    }

    @FXML
    void HoverHandled(MouseEvent event) {

    }
    @FXML
    void GoToScheduleButtonPressed(ActionEvent event) throws IOException {

        try {
            changeScene("SousChefSchedule.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }



    @FXML
    void RequestLeaveButtonPressed(ActionEvent event) throws IOException {

        try {
            changeScene("SousChefRequestLeave.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
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
    void GoToMealPrepPressed(ActionEvent event) throws IOException {
        try {
            changeScene("SousChefMealPrep.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Data data = Data.getDataInstance();

        EmployeeKMS emp = data.getMessage().getEmployeeObject();

        firstNameLabel.setText(emp.getFirstName());
        secondNameLabel.setText(emp.getLastName());
        userNameLabel.setText(emp.getUsername());
        empTypeLabel.setText(emp.getEmployeeType());
    }
}
