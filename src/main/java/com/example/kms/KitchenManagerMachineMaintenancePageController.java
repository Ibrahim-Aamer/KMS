package com.example.kms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class KitchenManagerMachineMaintenancePageController {

    @FXML
    private Button addTaskButton;

    @FXML
    private Button goToMachineMaintenance;

    @FXML
    private Button goToMakeSchedule;

    @FXML
    private Button goToRequestLeave;

    @FXML
    private Button goToSchedule;

    @FXML
    private Button homePage;

    @FXML
    private TextField maintenanceText;

    @FXML
    private TextField nameOfMachine;

    @FXML
    private Label newTaskPrompt;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button signoutButton;

    private Stage primaryStage;

    @FXML
    void initialize() {

        newTaskPrompt.setVisible(false);//prompt invisible


        this.primaryStage = primaryStage;

        Data data = Data.getDataInstance();
        data.setWord("I am data");
}

    @FXML
    void GoToMakeScheduleButtonPressed(ActionEvent event) {

        //Changing scene
        try {
            changeScene("KitchenManagerMakeSchedulePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void GoToScheduleButtonPressed(ActionEvent event) {
        //Changing scene
        try {
            changeScene("KitchenManagerSchedulePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void RequestLeaveButtonPressed(ActionEvent event) {

        //Changing scene
        try {
            changeScene("KitchenManagerRequestLeavePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void addTaskButtonPressed(ActionEvent event) {

        System.out.println(nameOfMachine.getText()+" "+maintenanceText.getText());
        System.out.println("Add task button");

        //Getting static data instance
        Data data = Data.getDataInstance();
        Message msg = data.getMessage();

        KitchenManager kitchenManager = new KitchenManager(msg.getEmployeeObject().getFirstName(),msg.getEmployeeObject().getLastName(),
                msg.getEmployeeObject().getUsername(),msg.getEmployeeObject().getPassword(),
                msg.getEmployeeObject().getEmployeeType());

        //Creating new task
        Task newTask = new Task();
        newTask.setTaskName(nameOfMachine.getText());
        newTask.setTaskDetails(maintenanceText.getText());

        //New task only added if text is not empty
        if(!nameOfMachine.getText().isEmpty() && !maintenanceText.getText().isEmpty())
        {
            System.out.println(nameOfMachine.getText()+"  "+ maintenanceText.getText());
            System.out.println("Task details : "+newTask.getTaskDetails());
            //Callin kitchen manager function to add new class to kms
            kitchenManager.MachineryMaintenance(newTask);
            newTaskPrompt.setVisible(false);//prompt invisible
        }
        else
        {
            System.out.println("empty");
            newTaskPrompt.setVisible(true);//prompt visible
        }

    }

    @FXML
    void goToMachineMaintenanceButtonPressed(ActionEvent event) {

    }

    @FXML
    void homePageButtonPressed(ActionEvent event) {


        System.out.println("Hello Home Page");

        try {
            changeScene("kitchenManagerHomePage.fxml", event);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void signOutButtonPressed(ActionEvent event)
    {

        System.out.println("Hello Home Page");

        try {
            changeScene("LoginPage.fxml", event);
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

}
